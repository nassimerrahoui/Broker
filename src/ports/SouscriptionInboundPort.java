package ports;

import basics.Souscription;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.SouscriptionServiceI;

public class SouscriptionInboundPort extends AbstractInboundPort implements SouscriptionServiceI {

	private static final long serialVersionUID = 1L;

	public SouscriptionInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, SouscriptionServiceI.class, owner);
		assert uri != null;
	}

	public void souscrire(final Souscription s) throws Exception {
		
		AbstractComponent.AbstractService<Void> task = new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).souscrire(s, s.uriInboundReception);
				return null;
			}
		};
		
		this.owner.handleRequestAsync(1, task);
	}
}