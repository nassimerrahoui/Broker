package ports;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.SouscriptionI;

public class CourtierSouscriptionInboundPort extends AbstractInboundPort implements SouscriptionI {

	private static final long serialVersionUID = 1L;

	public CourtierSouscriptionInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, SouscriptionI.class, owner);

		assert uri != null & owner != null;
	}

	public void souscrire(final Souscription s) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).souscrire(s, pluginURI);
				return null;
			}
		});

	}

	public void resiliation(Topic t) {
		// TODO Auto-generated method stub

	}

	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFilter(Filter filter) {
		// TODO Auto-generated method stub

	}

	public Topic getTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	public void resiliation(Topic t, String uriConsommateur) {
		// TODO Auto-generated method stub

	}

}
