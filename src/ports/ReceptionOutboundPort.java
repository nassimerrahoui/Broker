package ports;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.AbstractComponent;
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

		AbstractComponent.AbstractService<Void> task = new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((ReceptionServiceI) connector).recevoirMessage(msg, uri);
				return null;
			}
		};
		this.owner.handleRequestAsync(1, task);
	}

	public void recevoirNMessage(final ArrayList<Message> msg, final String uri) throws Exception {
		AbstractComponent.AbstractService<Void> task = new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((ReceptionServiceI) connector).recevoirNMessage(msg, uri);
				return null;
			}
		};
		this.owner.handleRequestAsync(1, task);
	}

}