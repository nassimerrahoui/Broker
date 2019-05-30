package ports;

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

	public void recevoirMessage(final Message msg, final String uri) throws Exception {
		((ReceptionServiceI) this.connector).recevoirMessage(msg, uri);
	}
}