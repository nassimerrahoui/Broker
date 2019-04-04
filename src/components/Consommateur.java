package components;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Filter;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.ports.PortI;
import interfaces.MessageServiceI;
import ports.MessageServiceInboundPort;
import ports.MessageServiceOutboundPort;

@OfferedInterfaces(offered = { MessageServiceI.class })
@RequiredInterfaces(required = { MessageServiceI.class })

public class Consommateur extends AbstractComponent {

	protected CopyOnWriteArrayList<Message> myMessages = new CopyOnWriteArrayList<Message>();
	protected CopyOnWriteArrayList<Topic> myTopics = new CopyOnWriteArrayList<Topic>();
	protected MessageServiceInboundPort receptionPort;
	protected MessageServiceOutboundPort souscriptionPort;

	public Consommateur() throws Exception {

		super(1, 1);
		String receptionPortName = java.util.UUID.randomUUID().toString();
		String souscriptionPortName = java.util.UUID.randomUUID().toString();

		receptionPort = new MessageServiceInboundPort(receptionPortName, this);
		this.addPort(receptionPort);
		receptionPort.publishPort();

		souscriptionPort = new MessageServiceOutboundPort(souscriptionPortName, this);
		this.addPort(souscriptionPort);
		souscriptionPort.publishPort();
		
		this.toggleTracing();
		this.tracer.setTitle("Consumer");
	}

	public void recevoirMessage(Message msg,String uriInboundPort) throws Exception {
		for (Topic t : myTopics) {
			if (msg.getTopicsURI().contains(t.getTopicURI())) {
				myMessages.add(msg);
				this.logMessage(this.receptionPort.getPortURI() + " a recu : " + msg.getContenu().toString());
				break;
			}
		}
	}

	public void recevoirNMessages(CopyOnWriteArrayList<Message> msgs) throws Exception {
		for (int i = 0; i < msgs.size(); i++) {
			if (msgs.get(i) == null)
				msgs.remove(i);
		}
		myMessages.addAll(msgs);
	}

	public void souscrire(Souscription s) throws Exception {
		this.logMessage("Souscription au topic "+s.topic+" sur le port "+s.uriInboundReception);
		this.myTopics.add(new Topic(s.topic));
		this.souscriptionPort.souscrire(s);
	}

	@Override
	public void start() throws ComponentStartException {
		this.logMessage("Lancement Consommateur");
		super.start();
	}

	@Override
	public void execute() throws Exception {
		super.execute();

		this.runTask(new AbstractTask() {
			public void run() {
				try {
					Filter f = new Filter();
					Souscription s = new Souscription("actu", f, receptionPort.getPortURI());
					souscrire(s);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void finalise() throws Exception {
		super.finalise();

	}

	@Override
	public void shutdown() throws ComponentShutdownException {
		try {
			PortI[] p = this.findPortsFromInterface(MessageServiceI.class);
			p[0].unpublishPort();
			p[1].unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdown();
	}
}
