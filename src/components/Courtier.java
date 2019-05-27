package components;

import java.util.ArrayList;
import java.util.Vector;
import basics.Filter;
import basics.ListSouscriptions;
import basics.ListTopics;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.ports.PortI;
import interfaces.PublicationServiceI;
import interfaces.ReceptionServiceI;
import interfaces.SouscriptionServiceI;
import ports.PublicationInboundPort;
import ports.PublicationOutboundPort;
import ports.ReceptionOutboundPort;
import ports.SouscriptionInboundPort;

@OfferedInterfaces(offered = { PublicationServiceI.class, SouscriptionServiceI.class })
@RequiredInterfaces(required = { PublicationServiceI.class, ReceptionServiceI.class })

public class Courtier extends AbstractComponent {

	// transfert a un courtier
	protected PublicationOutboundPort transfertOutPort;

	// liste des transferts effectues
	protected Vector<Message> transferred = new Vector<Message>();

	protected ReceptionOutboundPort envoiPort;
	protected PublicationInboundPort publicationPort;
	protected SouscriptionInboundPort souscriptionPort;
	protected ListTopics topics = new ListTopics();
	protected ListSouscriptions souscriptions = new ListSouscriptions();
	protected final Object lock = new Object();

	public Courtier(String outTransfertURI, String inTransfertURI) throws Exception {
		super(1, 0);

		createNewExecutorService("publication", 100, true);
		createNewExecutorService("souscription", 100, true);

		publicationPort = new PublicationInboundPort(inTransfertURI, this);

		String receptionPortURI = java.util.UUID.randomUUID().toString();
		envoiPort = new ReceptionOutboundPort(receptionPortURI, this);

		String souscriptionPortURI = java.util.UUID.randomUUID().toString();
		souscriptionPort = new SouscriptionInboundPort(souscriptionPortURI, this);

		transfertOutPort = new PublicationOutboundPort(outTransfertURI, this);

		this.addPort(publicationPort);
		this.addPort(envoiPort);
		this.addPort(souscriptionPort);
		this.addPort(transfertOutPort);

		publicationPort.publishPort();
		souscriptionPort.publishPort();
		envoiPort.publishPort();
		transfertOutPort.publishPort();

		this.tracer.setTitle(" Courtier : " + AbstractCVM.getCVM().logPrefix());
		this.tracer.setRelativePosition(1, 1);
		this.toggleTracing();
		this.toggleLogging();

	}
	
	public Courtier(String outTransfertURI, String inTransfertURI, int nb_consommateurs) throws Exception {
		super(1, 0);

		createNewExecutorService("publication", 5, true);
		createNewExecutorService("souscription", 5, true);

		for (int i = 0; i < nb_consommateurs; i++) {
			ReceptionOutboundPort r = new ReceptionOutboundPort("oportreception"+i, this);
			this.addPort(r);
			r.publishPort();
		}

		String souscriptionPortURI = java.util.UUID.randomUUID().toString();
		souscriptionPort = new SouscriptionInboundPort(souscriptionPortURI, this);

		publicationPort = new PublicationInboundPort(inTransfertURI, this);
		transfertOutPort = new PublicationOutboundPort(outTransfertURI, this);

		this.addPort(publicationPort);
		this.addPort(souscriptionPort);
		this.addPort(transfertOutPort);

		publicationPort.publishPort();
		souscriptionPort.publishPort();
		transfertOutPort.publishPort();

		this.tracer.setTitle(" Courtier : " + AbstractCVM.getCVM().logPrefix());
		this.tracer.setRelativePosition(1, 1);
		this.toggleTracing();
		this.toggleLogging();

	}

	public void addPortToCourtier(PortI p) throws Exception {
		this.addPort(p);
		p.publishPort();
	}

	public void publierMessage(Message m) throws Exception {
		if (m != null) {
			topics.addMesssageToTopic(m);
			for (String t : m.getTopicsURI()) {
				Topic topic = topics.getTopicByUri(t);
				for (String consommateurUri : souscriptions.getConsommateurUris()) {
					for (Souscription s : souscriptions.getSouscriptions().get(consommateurUri)) {
						if (s.topic.equals(topic.getTopicURI())) {
							envoieMessageAndPrint(m, consommateurUri);
						}
					}
				}
				topics.getTopicsMessagesMap().get(topic).remove(m);
			}
		}
	}

	public void publierNMessages(ArrayList<Message> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs);
	}

	public void createTopic(String uri) throws Exception {
		if (!(topics.existTopicURI(uri))) {
			topics.createTopic(uri);
			this.logMessage("Le topic " + uri + " a ete cree.");
		}
	}

	public void envoieMessageAndPrint(final Message msg, final String uriInboundConsumer) throws Exception {
		this.logMessage("Courtier envoi message a " + uriInboundConsumer + ": \n " + msg.toString());

		for (PortI p : findPortsFromInterface(ReceptionServiceI.class)) {
			if (p.getServerPortURI().equals(uriInboundConsumer)) {
				((ReceptionOutboundPort) p).recevoirMessage(msg, uriInboundConsumer);
			}
		}
	}

	public void souscrire(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (topics.existTopicURI(s.topic)) {
			this.logMessage(uriInBoundConsommateur + " souscrit au topic " + s.topic);
			souscriptions.addSouscriptionToConsommateur(s, uriInBoundConsommateur);
		} else {
			this.logMessage(uriInBoundConsommateur + " : Topic " + s.topic + " n'existe pas.");
		}
	}

	public void transfererMessage(Message msg) throws Exception {

		if (!transferred.contains(msg)) {
			this.logMessage("distribution d'un message");
			publierMessage(msg);
			transfertOutPort.transfererMessage(msg);
		} else {
			transferred.remove(msg);
			this.logMessage("message deja recu");
		}

	}

	public void firstTransmission(Message m) throws Exception {
		publierMessage(m);
		if (transfertOutPort.connected()) {
			transferred.add(m);
			transfertOutPort.transfererMessage(m);
			this.logMessage("transmission d'un message de mon producteur");
		}
	}

	public void setFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.modifyFilter(t, f, uriInBoundConsommateur);
	}

	public void shutdown() throws ComponentShutdownException {
		try {
			for (PortI p : portURIs2ports.values()) {
				if (p.isPublished())
					p.unpublishPort();
			}
		} catch (Exception e) {
			throw new ComponentShutdownException();
		}

		super.shutdown();
	}
}
