package ports;

import basics.Souscription;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.FilterI;
import interfaces.SouscriptionI;
import interfaces.TopicI;

public class CourtierSouscriptionInboundPort extends AbstractInboundPort implements SouscriptionI {

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
	
	private static final long serialVersionUID = 1L;

	public void resiliation(TopicI t) {
		// TODO Auto-generated method stub

	}

	public FilterI getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFilter(FilterI filter) {
		// TODO Auto-generated method stub

	}

	public TopicI getTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	public void resiliation(TopicI t, String uriConsommateur) {
		// TODO Auto-generated method stub

	}

}
