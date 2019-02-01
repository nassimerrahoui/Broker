package components;

import java.util.ArrayList;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.ListTopicsI;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;
import ports.CourtierInboundPort;
import ports.CourtierOutboundPort;


@RequiredInterfaces(required = { ReceptionI.class })
@OfferedInterfaces(offered = { PublicationI.class, ListTopicsI.class })

public class Courtier extends AbstractComponent {

	protected CourtierOutboundPort envoiePort;
	protected CourtierInboundPort publicationPort; // Publication du message à partir du producteur
	protected ListTopics topics = new ListTopics(); 

	public Courtier(String uri, String portURI) throws Exception {
		super(uri, 1, 0);

		publicationPort = new CourtierInboundPort("inbound-" + portURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();

		envoiePort = new CourtierOutboundPort("outbound-" + portURI, this);
		this.addPort(envoiePort);
		envoiePort.publishPort();

	}

	public void publierMessage(MessageI m) throws Exception {
		topics.addMesssageToTopic(m, m.getUriProducteur());
		System.out.println("Le message est publie :)");
		this.envoiePort.recevoirMessage(m);

	}

	public void publierNMessages(ArrayList<MessageI> msgs) throws Exception {
		topics.addNMesssageToTopic(msgs,msgs.get(0).getUriProducteur());
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
	
	public ArrayList<String> getUriTopics() {
		return topics.getUriTopics();
	}

	public void envoieMessageAndPrint(MessageI msg) throws Exception {
		System.out.println("Envoi du message vers le consommateur");
		this.envoiePort.recevoirMessage(msg);
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
