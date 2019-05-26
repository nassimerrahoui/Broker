package components;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import basics.Filter;
import basics.ListSouscriptions;
import basics.ListTopics;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.PublicationServiceI;
import interfaces.ReceptionServiceI;
import interfaces.SouscriptionServiceI;
import ports.PublicationInboundPort;
import ports.PublicationOutboundPort;
import ports.ReceptionOutboundPort;
import ports.SouscriptionInboundPort;

@OfferedInterfaces(offered = { PublicationServiceI.class, SouscriptionServiceI.class })
@RequiredInterfaces (required = { PublicationServiceI.class, ReceptionServiceI.class})

public class Courtier extends AbstractComponent {
	
	//transfert a un courtier
	protected PublicationOutboundPort transfertOutPort;
	
	//liste des transferts effectues
	protected Vector<Message> transferred = new Vector<Message>();

	protected ReceptionOutboundPort envoiPort;
	protected PublicationInboundPort publicationPort;
	protected SouscriptionInboundPort souscriptionPort;
	protected ListTopics topics = new ListTopics();
	protected ListSouscriptions souscriptions = new ListSouscriptions();
	protected final Object lock = new Object();

	public Courtier(String outTransfertURI, String inTransfertURI) throws Exception {
		super(1, 0);
		
		createNewExecutorService("publication", 5, true);
		createNewExecutorService("envoi", 5, true);

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

		this.tracer.setTitle(" Courtier");
		this.tracer.setRelativePosition(1, 1);
		this.toggleTracing();

	}

	public void publierMessage(Message m) throws Exception {
		topics.addMesssageToTopic(m);
		notifyConsommateurs();
	}

	public void publierNMessages(ArrayList<Message> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs);
	}

	public void createTopic(String uri) throws Exception {
		topics.createTopic(uri);
		this.logMessage("Le topic " + uri + " a ete cree.");
	}

	public void envoieMessageAndPrint(final Message msg, final String uriInboundConsumer) throws Exception {
		this.logMessage("Courtier envoi message a " + uriInboundConsumer + ": \n " + msg.toString());
		envoiPort.recevoirMessage(msg, uriInboundConsumer);
	}

	public void souscrire(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (topics.existTopicURI(s.topic)) {
			this.logMessage(uriInBoundConsommateur + " souscrit au topic " + s.topic);
			souscriptions.addSouscriptionToConsommateur(s, uriInBoundConsommateur);
		} else {
			this.logMessage(uriInBoundConsommateur + " : Topic " + s.topic + " n'existe pas.");
		}
	}
	
	public void transfererMessage(Message msg) throws Exception{
		if(!transferred.contains(msg)) {
			this.logMessage("distribution d'un message");
			publierMessage(msg);
			transfertOutPort.transfererMessage(msg);
		}
		else {
			transferred.remove(msg);
			this.logMessage("message me revient");
		}
	}
	
	public void firstTransmission(Message m) throws Exception{
		publierMessage(m);
		if(transfertOutPort.connected()) {
			transferred.add(m);
			transfertOutPort.transfererMessage(m);
			this.logMessage("transmission d'un message de mon producteur");
		}
	}

	public void setFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.modifyFilter(t, f, uriInBoundConsommateur);
	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
	}

	@Override
	public void finalise() throws Exception {
		super.finalise();
	}

	public void shutdown() throws ComponentShutdownException {
		try {
			envoiPort.unpublishPort();
			publicationPort.unpublishPort();
			souscriptionPort.unpublishPort();
			transfertOutPort.unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException();
		}

		super.shutdown();
	}

	public void notifyConsommateurs() throws Exception {

		synchronized (lock) {
			ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>> subs = souscriptions.getSouscriptions();
			ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>> tops = topics.getTopics();
	
			for (ConcurrentHashMap.Entry<Topic, ConcurrentLinkedQueue<Message>> entry_topic : tops.entrySet()) {
				Topic topic = entry_topic.getKey();
				ConcurrentLinkedQueue<Message> msgs = entry_topic.getValue();
				
				// Message a envoye
				Message msg = msgs.poll();
	
				if(msg != null) {
					for (ConcurrentHashMap.Entry<String, ConcurrentHashMap<String, Souscription>> entry_sub : subs.entrySet()) {
	
						ConcurrentHashMap<String, Souscription> sub = entry_sub.getValue();
						if (sub.containsKey(topic.getTopicURI())) {
							String uri = sub.get(topic.getTopicURI()).uriInboundReception;
							this.logMessage(uri);
							envoieMessageAndPrint(msg, uri);
	
						}
	
					}	
				}
			}
		}
	}

}
