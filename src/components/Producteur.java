package components;

import java.util.ArrayList;

import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.ListTopicsI;
import interfaces.MessageI;
import interfaces.PublicationI;
import ports.ProducteurOutboundPort;

@RequiredInterfaces(required = { PublicationI.class, ListTopicsI.class })
public class Producteur extends AbstractComponent {

	protected ProducteurOutboundPort publicationPort;

	public Producteur(String uri, String outboundPortURI) throws Exception {
		super(uri, 0, 1);

		publicationPort = new ProducteurOutboundPort(outboundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();
		// faire un toggletracing pour afficher les  logs | faireun togglelogging pour enregistrer dans un fichier
		this.toggleTracing();
		this.tracer.setTitle("Producteur");
		this.tracer.setRelativePosition(1, 1);
	}

	public void publishMessageAndPrint(MessageI msg) throws Exception {
		this.publicationPort.publierMessage(msg);
		this.logMessage(this.getComponentDefinitionClassName() + " publie un nouveau msg : \n" + msg.getContenu());

	}
	
	public void createTopic(String uri, String uriProducteur) throws Exception {
		this.publicationPort.createTopic(uri, uriProducteur);
	}
	
	public void deleteTopic(String uri, String uriProd) throws Exception {
		this.publicationPort.deleteTopic(uri, uriProd);
	}
	
	public Boolean existTopicURI(String uri) throws Exception {
		return this.publicationPort.existTopicURI(uri);
	}
	
	public ArrayList<String> getUriTopics() throws Exception {
		return this.publicationPort.getUriTopics();
	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		this.logMessage("Lancement du composant Producteur...");
	}

	@Override
	public void finalise() throws Exception {
		this.logMessage("Arret du composant Producteur...");

		this.publicationPort.doDisconnection();
		this.publicationPort.unpublishPort();

		super.finalise();
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		this.runTask(
				new AbstractTask() {
					public void run() {
						try {
							ArrayList<String> l = new ArrayList<String>();
							l.add("Sujet1");
							l.add("Sujet2");
							MessageI msg = new Message("String sérialisable du composant","p1",l);
							((Producteur)this.owner).publishMessageAndPrint(msg);
							//((Producteur)this.owner).createTopic("Sport", "prod1");
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}) ;
		
	}

}
