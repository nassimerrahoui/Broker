package components;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
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

		this.tracer.setTitle(" Producteur");
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

		this.scheduleTask(new AbstractComponent.AbstractTask() {
			@Override
			public void run() {
				if (AbstractCVM.isDistributed) {
					try {
						ArrayList<String> topics = new ArrayList<String>();
						topics.add("A");
						topics.add("B");

						switch (AbstractCVM.getCVM().logPrefix()) {
						case "jvm_courtier_1":
							Message m1 = new Message("Message numero 1.", "p1", topics);
							Message m2 = new Message("Message numero 2.", "p1", "C");
							publishMessageAndPrint(m1);
							publishMessageAndPrint(m2);
							break;

						case "jvm_courtier_2":
							Message m3 = new Message("Message numero 3.", "p1", topics);
							Message m4 = new Message("Message numero 4.", "p1", "C");
							publishMessageAndPrint(m3);
							publishMessageAndPrint(m4);
							break;

						case "jvm_courtier_3":
							Message m5 = new Message("Message numero 5.", "p1", topics);
							Message m6 = new Message("Message numero 6.", "p1", "C");
							publishMessageAndPrint(m5);
							publishMessageAndPrint(m6);
							break;

						case "jvm_courtier_4":
							Message m7 = new Message("Message numero 7.", "p1", topics);
							Message m8 = new Message("Message numero 8.", "p1", "C");
							publishMessageAndPrint(m7);
							publishMessageAndPrint(m8);
							break;

						default:
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
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
			}
		}, 2000, TimeUnit.MILLISECONDS);
	}

}