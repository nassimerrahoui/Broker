package components;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.PublicationServiceI;
import ports.PublicationOutboundPort;

@RequiredInterfaces(required = { PublicationServiceI.class })
public class Producteur extends AbstractComponent {

	protected PublicationOutboundPort publicationPort;

	public Producteur() throws Exception {
		super(1, 1);
		String outBoundPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new PublicationOutboundPort(outBoundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();
		
		this.tracer.setTitle("Producteur");
		this.tracer.setRelativePosition(1, 1);
		this.toggleTracing();

	}

	public void publishMessageAndPrint(Message msg) throws Exception {
		this.logMessage(this.publicationPort.getPortURI() + " a publie : " + msg.toString());
		this.publicationPort.publierMessage(msg);

	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		this.runTask(new AbstractTask() {

			public void run() {
				try {
					publicationPort.createTopic("A");
					publicationPort.createTopic("B");
					publicationPort.createTopic("C");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public void finalise() throws Exception {
		this.publicationPort.doDisconnection();
		this.publicationPort.unpublishPort();

		super.finalise();
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		Thread.sleep(1000);
		this.runTask(new AbstractTask() {
			public void run() {
				try {
					ArrayList<String> topics = new ArrayList<String>();
					topics.add("A");
					topics.add("B");
					Message m1 = new Message("Message numero 1.", "p1", topics);
					Message m2 = new Message("Message numero 2.", "p1", "C");
					((Producteur) this.owner).publishMessageAndPrint(m1);
					((Producteur) this.owner).publishMessageAndPrint(m2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
