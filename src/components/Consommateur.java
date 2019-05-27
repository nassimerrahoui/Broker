package components;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import basics.Filter;
import basics.Message;
import basics.Souscription;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.ReceptionServiceI;
import interfaces.SouscriptionServiceI;
import ports.ReceptionInboundPort;
import ports.SouscriptionOutboundPort;

@OfferedInterfaces(offered = { ReceptionServiceI.class })
@RequiredInterfaces(required = { SouscriptionServiceI.class })

public class Consommateur extends AbstractComponent {

	protected Vector<Message> myMessages = new Vector<Message>();
	protected ReceptionInboundPort receptionPort;
	protected SouscriptionOutboundPort souscriptionPort;
	protected long lastDateMessage = System.currentTimeMillis();
	protected Object lock = new Object();

	public Consommateur() throws Exception {

		super(1, 1);

		String souscriptionPortName = java.util.UUID.randomUUID().toString();
		souscriptionPort = new SouscriptionOutboundPort(souscriptionPortName, this);
		
		String receptionPortName = java.util.UUID.randomUUID().toString();
		receptionPort = new ReceptionInboundPort(receptionPortName, this);
		
		this.addPort(souscriptionPort);
		this.addPort(receptionPort);
		souscriptionPort.publishPort();
		receptionPort.publishPort();

		this.tracer.setTitle(" Consommateur : " + AbstractCVM.getCVM().logPrefix());
		this.tracer.setRelativePosition(1, 1);
		this.toggleTracing();
		this.toggleLogging();
	}
	
	public long getLastDateMessage() {
		return lastDateMessage;
	}

	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception {
		synchronized (lock) {
			myMessages.add(msg);
			this.logMessage(this.receptionPort.getPortURI() + " a recu : " + msg.getContenu().toString());
			if(lastDateMessage < msg.getDatePublication()) {
				lastDateMessage = msg.getDatePublication();
			}
		}
	}

	public void recevoirNMessages(ArrayList<Message> msgs) throws Exception {
		for (int i = 0; i < msgs.size(); i++) {
			if (msgs.get(i) == null)
				msgs.remove(i);
		}
		myMessages.addAll(msgs);
	}

	public void souscrire(Souscription s) throws Exception {
		this.logMessage("Souscription au topic " + s.topic + " sur le port " + s.uriInboundReception);
		this.souscriptionPort.souscrire(s);
	}

	@Override
	public void start() throws ComponentStartException {
		super.start();
		try {
			this.scheduleTask(
				new AbstractComponent.AbstractTask() {
					@Override
					public void run() {
						try {
							Filter f = new Filter();
							f.setContenu("numero 0");
							f.setDatePublication(System.currentTimeMillis());
							Souscription s = new Souscription("A", f, receptionPort.getPortURI());
							souscrire(s);
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				1000, TimeUnit.MILLISECONDS) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute() throws Exception {
		super.execute();
	}

	@Override
	public void finalise() throws Exception {
		super.finalise();

	}

	@Override
	public void shutdown() throws ComponentShutdownException {
		try {
			receptionPort.unpublishPort();
			souscriptionPort.unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdown();
	}
}
