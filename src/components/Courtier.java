package components;

import java.util.concurrent.CopyOnWriteArrayList;

import basics.Filter;
import basics.ListSouscriptions;
import basics.ListTopics;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageServiceI;
import ports.MessageServiceInboundPort;
import ports.MessageServiceOutboundPort;

@RequiredInterfaces(required = { MessageServiceI.class })
@OfferedInterfaces(offered = { MessageServiceI.class, MessageServiceI.class })

public class Courtier extends AbstractComponent {

	protected MessageServiceOutboundPort envoiePort;
	protected MessageServiceInboundPort publicationPort;
	protected MessageServiceInboundPort souscriptionPort;

	protected ListTopics topics = new ListTopics();
	protected ListSouscriptions souscriptions = new ListSouscriptions();

	public Courtier(String uri) throws Exception {
		super(uri, 1, 0);

		String publicationPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new MessageServiceInboundPort(publicationPortURI, this);

		String envoiPortURI = java.util.UUID.randomUUID().toString();
		envoiePort = new MessageServiceOutboundPort(envoiPortURI, this);

		String souscriptionPortURI = java.util.UUID.randomUUID().toString();
		souscriptionPort = new MessageServiceInboundPort(souscriptionPortURI, this);

		this.addPort(publicationPort);
		this.addPort(envoiePort);
		this.addPort(souscriptionPort);
		publicationPort.publishPort();
		souscriptionPort.publishPort();
		envoiePort.publishPort();
		
		// Faire un toggletracing pour afficher les logs | faire 
		// Un togglelogging pour enregistrer dans un fichier
		this.toggleTracing();
		this.tracer.setTitle("Courtier");
		this.tracer.setRelativePosition(1, 1);

	}

	public void publierMessage(Message m) throws Exception {
		topics.addMesssageToTopic(m, m.getUriProducteur());
		// Quels sont les autres formes de contenu
		this.envoiePort.recevoirMessage(m);

	}

	public void publierNMessages(CopyOnWriteArrayList<Message> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs, msgs.get(0).getUriProducteur());
		this.envoiePort.recevoirNMessage(msgs);
	}

	public void createTopic(String uri) throws Exception {
		topics.createTopic(uri);
		this.logMessage("Le topic " + uri + " a ete cree.");
	}

	public void deleteTopic(String uri) {
		topics.deleteTopic(uri);
		this.logMessage("Le topic " + uri + " a ete supprime.");
	}

	public void envoieMessageAndPrint(Message msg) throws Exception {
		this.envoiePort.recevoirMessage(msg);
	}

	public void souscrire(Souscription s, String uriConsommateur) throws Exception {
		souscriptions.addSouscriptionToConsommateur(s, uriConsommateur);
	}
	
	public void setFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.modifyFilter(t, f, uriInBoundConsommateur);
	}
	
	public void resiliation(Topic t, String uriConsommateur) {
		//TODO
	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		this.logMessage("Lancement du courtier");

	}

	@Override
	public void finalise() throws Exception {
		this.logMessage("Arret du courtier.");
		super.finalise();
	}

}
