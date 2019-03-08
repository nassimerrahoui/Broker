package components;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Filter;
import basics.Message;
import basics.Souscription;
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

	protected CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<Message>();
	protected MessageServiceInboundPort receptionPort;
	protected MessageServiceOutboundPort souscriptionPort;

	public Consommateur(String uri) throws Exception {

		super(uri, 1, 0);
		String receptionPortName = java.util.UUID.randomUUID().toString();
		String souscriptionPortName = java.util.UUID.randomUUID().toString();

		receptionPort = new MessageServiceInboundPort(receptionPortName, this);
		this.addPort(receptionPort);
		receptionPort.publishPort();

		souscriptionPort = new MessageServiceOutboundPort(souscriptionPortName, this);
		this.addPort(souscriptionPort);
		souscriptionPort.publishPort();
	}

	public void recevoirMessage(Message msg) throws Exception {
		/** TODO if a remplacer par si ce sont des messages des nos souscriptions on recoit **/
		if(msg.getTopicsURI().contains("hockey")) {
			messages.add(msg);
			System.out.println(this.receptionPort.getPortURI() + " a recu : " + msg.getContenu().toString());
		}
		else {
			System.out.println(this.receptionPort.getPortURI() + " non recu : " + msg.getContenu().toString());
		}
	}

	public void recevoirNMessages(CopyOnWriteArrayList<Message> msgs) throws Exception {
		for (int i = 0; i < msgs.size(); i++) {
			if (msgs.get(i) == null)
				msgs.remove(i);
		}
		messages.addAll(msgs);
	}

	public void souscrire(Souscription s) throws Exception {
		this.souscriptionPort.souscrire(s);
	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
	}

	@Override
	public void execute() throws Exception {
		super.execute();

		this.runTask(new AbstractTask() {
			public void run() {
				try {
					Filter f = new Filter();
					Souscription s = new Souscription("hockey", f, receptionPort.getPortURI());
					souscriptionPort.souscrire(s);
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
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdown();
	}
}
