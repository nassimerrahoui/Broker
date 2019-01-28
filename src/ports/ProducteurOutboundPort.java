package ports;

import java.util.ArrayList;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;

public class ProducteurOutboundPort extends AbstractOutboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public ProducteurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}
	
	public void publierMessage(MessageI msg) throws Exception {
		((PublicationI) this.connector).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception {
		((PublicationI) this.connector).publierNMessage(msgs);
	}

}
