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

	public Producteur(String uri) throws Exception {
		super(uri, 0, 1);
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
			this.publicationPort.createTopic("chasse");
			this.publicationPort.createTopic("cinema");
			this.publicationPort.createTopic("hockey");
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
					topics.add("chasse");
					topics.add("cinema");
					Message m1 = new Message("Un nouveau film sur la chasse est sorti.", "p1", topics);
					Message m2 = new Message("L'equipe du Canada a gagne la final de hockey !", "p1", "hockey");
					((Producteur) this.owner).publishMessageAndPrint(m1);
					((Producteur) this.owner).publishMessageAndPrint(m2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
