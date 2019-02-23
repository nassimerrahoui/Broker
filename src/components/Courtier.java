package components;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.ListSouscriptions;
import basics.ListTopics;
import basics.Message;
import basics.Souscription;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.PublicationI;
import interfaces.ReceptionI;
import interfaces.SouscriptionI;
import ports.CourtierInboundPort;
import ports.CourtierOutboundPort;
import ports.CourtierSouscriptionInboundPort;

@RequiredInterfaces(required = { ReceptionI.class })
@OfferedInterfaces(offered = { PublicationI.class, SouscriptionI.class })

public class Courtier extends AbstractComponent {

	protected CourtierOutboundPort envoiePort;
	protected CourtierInboundPort publicationPort; // Publication du message à partir du producteur
	protected CourtierSouscriptionInboundPort souscriptionPort;

	protected ListTopics topics = new ListTopics();
	protected ListSouscriptions souscriptions = new ListSouscriptions();

	public Courtier(String uri) throws Exception {
		super(uri, 1, 0);

		String publicationPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new CourtierInboundPort(publicationPortURI, this);

		String envoiPortURI = java.util.UUID.randomUUID().toString();
		envoiePort = new CourtierOutboundPort(envoiPortURI, this);

		String souscriptionPortURI = java.util.UUID.randomUUID().toString();
		souscriptionPort = new CourtierSouscriptionInboundPort(souscriptionPortURI, this);

		this.addPort(publicationPort);
		this.addPort(envoiePort);
		this.addPort(souscriptionPort);
		publicationPort.publishPort();
		souscriptionPort.publishPort();
		envoiePort.publishPort();

	}

	public void publierMessage(Message m) throws Exception {
		topics.addMesssageToTopic(m, m.getUriProducteur());
		System.out.println("Le message est publie :)");
		this.envoiePort.recevoirMessage(m);

	}

	public void publierNMessages(CopyOnWriteArrayList<Message> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs, msgs.get(0).getUriProducteur());
		System.out.println("Les messages sont publies :)");
		this.envoiePort.recevoirNMessage(msgs);
	}

	public void createTopic(String uri, String uriProducteur) throws Exception {
		topics.createTopic(uri, uriProducteur);
		System.out.println("Votre topic a ete cree.");
	}

	public void deleteTopic(String uri, String uriProd) {
		topics.deleteTopic(uri, uriProd);
	}

	public Boolean existTopicURI(String uri) {
		return topics.existTopicURI(uri);
	}

	public CopyOnWriteArrayList<String> getUriTopics() {
		return topics.getUriTopics();
	}

	public void envoieMessageAndPrint(Message msg) throws Exception {
		System.out.println("Envoi du message vers le consommateur...");
		this.envoiePort.recevoirMessage(msg);
	}

	public void souscrire(Souscription s, String uriConsommateur) throws Exception {
		souscriptions.addSouscriptionToConsommateur(s, uriConsommateur);
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
