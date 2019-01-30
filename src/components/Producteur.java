package components;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageI;
import interfaces.PublicationI;
import ports.ProducteurOutboundPort;

@RequiredInterfaces(required = { PublicationI.class })
public class Producteur extends AbstractComponent {

	protected ProducteurOutboundPort publicationPort;

	public Producteur(String uri, String outboundPortURI) throws Exception {
		super(uri, 0, 1);

		publicationPort = new ProducteurOutboundPort(outboundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();

		this.tracer.setTitle("Producteur");
		this.tracer.setRelativePosition(1, 1);
	}

	public void publishMessageAndPrint(MessageI msg) throws Exception {
		this.publicationPort.publierMessage(msg);
		this.logMessage(this.getComponentDefinitionClassName() + " publie un nouveau msg : \n" + msg.getContenu());

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
							MessageI msg = new Message("String sérialisable du composant","p1");
							((Producteur)this.owner).publishMessageAndPrint(msg);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}) ;
		
	}

}
