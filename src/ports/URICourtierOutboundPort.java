package ports;

import java.util.ArrayList;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;

public class URICourtierOutboundPort extends AbstractOutboundPort
implements ReceptionI{

	private static final long serialVersionUID = 1L;

	public URICourtierOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}
	
	public URICourtierOutboundPort(ComponentI owner) throws Exception {
		super(PublicationI.class,owner);
		assert owner != null;
	}

	public void recevoirMessage(MessageI msg) throws Exception {
		((ReceptionI)this.connector).recevoirMessage(msg);
	}

	public void recevoirNMessage(ArrayList<MessageI> msg) throws Exception {
		((ReceptionI)this.connector).recevoirNMessage(msg);
	}
	
}
