package connectors;

import basics.Souscription;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.FilterI;
import interfaces.SouscriptionI;
import interfaces.TopicI;

public class SouscriptionConnector extends AbstractConnector implements SouscriptionI {

	public void souscrire(Souscription s) throws Exception {
		((SouscriptionI) this.offering).souscrire(s);

	}

	public void resiliation(TopicI t, String uriConsommateur) {
		((SouscriptionI) this.offering).resiliation(t, uriConsommateur);

	}

	public void setFilter(FilterI filter) {
		((SouscriptionI) this.offering).setFilter(filter);

	}

}
