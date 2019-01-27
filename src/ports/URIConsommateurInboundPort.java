package ports;

import java.util.ArrayList;
import components.Consommateur;
import components.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ReceptionI;

public class URIConsommateurInboundPort extends AbstractInboundPort implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public URIConsommateurInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert uri != null && owner instanceof Consommateur;
	}

	public void recevoirMessage(String uri, Message msg) {
		
	}

	public void recevoirNMessage(String uri, ArrayList<Message> msg) {
		
	}


}