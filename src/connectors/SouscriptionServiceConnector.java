package connectors;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.SouscriptionServiceI;

public class SouscriptionServiceConnector extends AbstractConnector implements SouscriptionServiceI {
	
	public void souscrire(Souscription s) throws Exception {
		((SouscriptionServiceI) this.offering).souscrire(s);
	}

	public void setFilter(Topic t, Filter filter, String uriInBoundConsommateur) throws Exception {
		((SouscriptionServiceI) this.offering).setFilter(t, filter, uriInBoundConsommateur);
	}

}
