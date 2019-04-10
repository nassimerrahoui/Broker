package ports;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ReceptionServiceI;

public class ReceptionOutboundPort extends AbstractOutboundPort implements ReceptionServiceI {

	public ReceptionOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionServiceI.class, owner);
		assert uri != null && owner != null;
	}

	private static final long serialVersionUID = 1L;

	public void recevoirMessage(Message msg, String uri) throws Exception {
		((ReceptionServiceI) this.connector).recevoirMessage(msg, uri);
	}

	public void recevoirNMessage(ArrayList<Message> msg, String uri) throws Exception {
		((ReceptionServiceI) this.connector).recevoirNMessage(msg, uri);
	}

}
