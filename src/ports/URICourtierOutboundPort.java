package ports;

import java.util.ArrayList;

import components.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;


public class URICourtierOutboundPort extends AbstractOutboundPort
implements ReceptionI{

	public URICourtierOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}
	
	public URICourtierOutboundPort(ComponentI owner) throws Exception {
		super(PublicationI.class,owner);
		assert owner != null;
	}

	public String recevoirMessage(MessageI m) {
		return ((ReceptionI)this.connector).recevoirMessage(m);
	}

	public void recevoirNMessage( ArrayList<MessageI> msg) throws Exception {
		return ((ReceptionI)this.connector).recevoirNMessage(m);
		
	}
	
}
