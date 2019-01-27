package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PublicationI;

public class URIProducteurOutboundPort extends AbstractOutboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public URIProducteurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}

	@Override
	public void publierMessage() {

	}

	@Override
	public void publierNMessage() {

	}

}
