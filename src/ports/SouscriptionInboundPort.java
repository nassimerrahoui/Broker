package ports;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
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
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).souscrire(s, s.uriInboundReception);
				return null;
			}
		});

	}

	public void setFilter(final Topic t, final Filter filter, final String uriInBoundConsommateur) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).setFilter(t, filter, uriInBoundConsommateur);
				return null;
			}
		});

	}
}