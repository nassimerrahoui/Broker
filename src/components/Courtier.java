package components;

import java.util.ArrayList;
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
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.PublicationServiceI;
import interfaces.SouscriptionServiceI;
import ports.PublicationInboundPort;
import ports.ReceptionOutboundPort;
import ports.SouscriptionInboundPort;

@OfferedInterfaces(offered = { PublicationServiceI.class, SouscriptionServiceI.class })

public class Courtier extends AbstractComponent {

	protected ReceptionOutboundPort envoiPort;
	protected PublicationInboundPort publicationPort;
	protected SouscriptionInboundPort souscriptionPort;
	protected ListTopics topics = new ListTopics();
	protected ListSouscriptions souscriptions = new ListSouscriptions();

	public Courtier() throws Exception {
		super(1, 0);

		createNewExecutorService("publication", 100, false);
		createNewExecutorService("envoi", 100, false);

		String publicationPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new PublicationInboundPort(publicationPortURI, this);

		String receptionPortURI = java.util.UUID.randomUUID().toString();
		envoiPort = new ReceptionOutboundPort(receptionPortURI, this);

		String souscriptionPortURI = java.util.UUID.randomUUID().toString();
		souscriptionPort = new SouscriptionInboundPort(souscriptionPortURI, this);

		this.addPort(publicationPort);
		this.addPort(envoiPort);
		this.addPort(souscriptionPort);
		publicationPort.publishPort();
		souscriptionPort.publishPort();
		envoiPort.publishPort();

		this.toggleTracing();
		this.tracer.setTitle("Courtier");
		this.tracer.setRelativePosition(1, 1);

	}

	public void publierMessage(Message m) throws Exception {
		topics.addMesssageToTopic(m, m.getUriProducteur());
		notifyConsommateurs();

	}

	public void publierNMessages(ArrayList<Message> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs, msgs.get(0).getUriProducteur());
	}

	public void createTopic(String uri) throws Exception {
		topics.createTopic(uri);
		this.logMessage("Le topic " + uri + " a ete cree.");
	}

	public void envoieMessageAndPrint(final Message msg, final String uriInboundConsumer) throws Exception {
		this.logMessage("Courtier envoi message a " + uriInboundConsumer + ": \n " + msg.toString());
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

	public void setFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.modifyFilter(t, f, uriInBoundConsommateur);
	}

	/** TODO **/
	public void resiliation(Topic t, String uriInBoundConsommateur) {
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
		} catch (Exception e) {
			throw new ComponentShutdownException();
		}

		super.shutdown();
	}

	public void notifyConsommateurs() throws Exception {

		ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>> subs = souscriptions.getSouscriptions();
		ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>> tops = topics.getTopics();

		for (ConcurrentHashMap.Entry<Topic, ConcurrentLinkedQueue<Message>> entry_topic : tops.entrySet()) {
			Topic topic = entry_topic.getKey();
			ConcurrentLinkedQueue<Message> msgs = entry_topic.getValue();

			for (ConcurrentHashMap.Entry<String, ConcurrentHashMap<String, Souscription>> entry_sub : subs.entrySet()) {

				ConcurrentHashMap<String, Souscription> sub = entry_sub.getValue();
				if (sub.containsKey(topic.getTopicURI())) {
					String uri = sub.get(topic.getTopicURI()).uriInboundReception;
					envoieMessageAndPrint(msgs.poll(), uri);

				}

			}
		}
	}

}
