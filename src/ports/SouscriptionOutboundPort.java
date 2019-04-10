package ports;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.SouscriptionServiceI;

public class SouscriptionOutboundPort extends AbstractOutboundPort implements SouscriptionServiceI {

	public SouscriptionOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, SouscriptionServiceI.class, owner);
		assert uri != null && owner != null;
	}

	private static final long serialVersionUID = 1L;

	public void resiliation(Topic t, String uriConsommateur) throws Exception {
		((SouscriptionServiceI) this.connector).resiliation(t, uriConsommateur);
	}

	public void souscrire(Souscription s) throws Exception {
		((SouscriptionServiceI) this.connector).souscrire(s);
	}

	public void setFilter(Topic t, Filter filter, String uriInBoundConsommateur) throws Exception {
		((SouscriptionServiceI) this.connector).setFilter(t, filter, uriInBoundConsommateur);
	}

}
