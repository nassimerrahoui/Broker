package ports;

import java.util.ArrayList;
import components.Consommateur;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageI;
import interfaces.ReceptionI;

public class ConsommateurInboundPort extends AbstractInboundPort implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public ConsommateurInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert uri != null && owner instanceof Consommateur;
	}

	public void recevoirMessage(final MessageI msg) throws Exception {

		this.owner.handleRequestAsync(
				new AbstractComponent.AbstractService<Void>() {

					public Void call() throws Exception {
						((Consommateur) this.getOwner()).recevoirMessage(msg);
						return null;
					}
				}) ;

	}

	public void recevoirNMessage(ArrayList<MessageI> msgs) throws Exception {

		((ReceptionI) this.owner).recevoirNMessage(msgs);

	}

}