package connectors;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.SouscriptionI;

public class SouscriptionConnector extends AbstractConnector implements SouscriptionI {

	public void souscrire(Souscription s) throws Exception {
		((SouscriptionI) this.offering).souscrire(s);

	}

	public void resiliation(Topic t, String uriConsommateur) {
		((SouscriptionI) this.offering).resiliation(t, uriConsommateur);

	}

	public void setFilter(Filter filter) {
		((SouscriptionI) this.offering).setFilter(filter);

	}

}
