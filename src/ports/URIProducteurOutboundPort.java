package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;

public class URIProducteurOutboundPort extends AbstractOutboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public URIProducteurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}
	
	public URIProducteurOutboundPort(ComponentI owner) throws Exception {
		super(PublicationI.class,owner);
		assert owner != null;
	}

	public MessageI publierMessage(MessageI m) throws Exception {
		return ((PublicationI)this.connector).publierMessage(m);
	}
 /*
	public MessageI [] publierNMessage(int numberOfMsg) {
		return ((PublicationI)this.connector).publierNMessage(numberOfMsg);
	}*/

	public MessageI[] publierNMessage(int numberOfMsg, MessageI m) {
		// TODO Auto-generated method stub
		return null;
	}

}
