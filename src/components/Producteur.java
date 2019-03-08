package components;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.PublicationI;
import ports.ProducteurOutboundPort;

@RequiredInterfaces(required = { PublicationI.class })
public class Producteur extends AbstractComponent {

	protected ProducteurOutboundPort publicationPort;

	public Producteur(String uri) throws Exception {
		super(uri, 0, 1);

		String outBoundPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new ProducteurOutboundPort(outBoundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();
		// faire un toggletracing pour afficher les logs | faireun togglelogging pour
		// enregistrer dans un fichier
		this.toggleTracing();
		this.tracer.setTitle("Producteur");
		this.tracer.setRelativePosition(1, 1);
	}

	public void publishMessageAndPrint(Message msg) throws Exception {
		this.publicationPort.publierMessage(msg);
		this.logMessage(this.getComponentDefinitionClassName() + " publie un nouveau msg : \n" + msg.getContenu());

	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		this.logMessage("Lancement du composant Producteur...");
		try {
			this.publicationPort.createTopic("chasse");
			this.publicationPort.createTopic("cinema");
			this.publicationPort.createTopic("hockey");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		this.runTask(new AbstractTask() {
			public void run() {
				try {
					ArrayList<String> topics = new ArrayList<String>();
					topics.add("chasse");
					topics.add("cinema");
					Message msg = new Message("Message pour les topics chasse et cinema !", "p1", topics);
					((Producteur) this.owner).publishMessageAndPrint(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
