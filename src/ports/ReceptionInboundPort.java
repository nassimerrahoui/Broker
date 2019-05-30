package ports;

import basics.Message;
import components.Consommateur;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ReceptionServiceI;

public class ReceptionInboundPort extends AbstractInboundPort implements ReceptionServiceI {

	private static final long serialVersionUID = 1L;

	public ReceptionInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionServiceI.class, owner);
		assert uri != null;
	}

	public void recevoirMessage(final Message msg, final String uriInboundConsumer) throws Exception {

		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {

				((Consommateur) this.owner).recevoirMessage(msg, uriInboundConsumer);

				return null;
			}
		});

	}
}