package ports;

import java.util.ArrayList;
import components.Consommateur;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageI;
import interfaces.ReceptionI;

public class URIConsommateurInboundPort extends AbstractInboundPort implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public URIConsommateurInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert uri != null && owner instanceof Consommateur;
	}

	public void recevoirMessage(MessageI msg) throws Exception {
		// this.getOwner().handleRequestAsync(
		//
		// new AbstractComponent.AbstractService<Void>() {
		// @Override
		// public Void call() throws Exception {
		//
		// }
		// }
		//
		// );

	}

	public void recevoirNMessage(ArrayList<MessageI> msg) {

	}

}