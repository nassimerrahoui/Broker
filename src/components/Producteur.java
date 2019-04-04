package components;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageServiceI;
import ports.MessageServiceOutboundPort;

@RequiredInterfaces(required = { MessageServiceI.class })
public class Producteur extends AbstractComponent {

	protected MessageServiceOutboundPort publicationPort;

	public Producteur() throws Exception {
		super(0, 1);
		String outBoundPortURI = java.util.UUID.randomUUID().toString();
		publicationPort = new MessageServiceOutboundPort(outBoundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.publishPort();

	}

	public void publishMessageAndPrint(Message msg) throws Exception {
		this.publicationPort.publierMessage(msg);

	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		try {
			this.publicationPort.createTopic("actu");
			this.publicationPort.createTopic("cinema");
			this.publicationPort.createTopic("culture");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		this.runTask(new AbstractTask() {
			public void run() {
				try {
					ArrayList<String> topics = new ArrayList<String>();
					topics.add("culture");
					topics.add("cinema");
					Message m1 = new Message("Un nouveau film est sorti.", "p1", topics);
					Message m2 = new Message("Les gilets jaunes acte 21.", "p1", "actu");
					((Producteur) this.owner).publishMessageAndPrint(m1);
					((Producteur) this.owner).publishMessageAndPrint(m2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
