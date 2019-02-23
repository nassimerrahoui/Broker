package ports;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ReceptionI;

public class CourtierOutboundPort extends AbstractOutboundPort implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public CourtierOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert uri != null && owner != null;
	}

	public CourtierOutboundPort(ComponentI owner) throws Exception {
		super(ReceptionI.class, owner);
		assert owner != null;
	}

	public void recevoirMessage(Message msg) throws Exception {
		((ReceptionI) this.connector).recevoirMessage(msg);
	}

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msg) throws Exception {
		((ReceptionI) this.connector).recevoirNMessage(msg);
	}

}
