package connectors;

import basics.Souscription;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.SouscriptionServiceI;

public class SouscriptionServiceConnector extends AbstractConnector implements SouscriptionServiceI {
	
	public void souscrire(Souscription s) throws Exception {
		((SouscriptionServiceI) this.offering).souscrire(s);
	}
}
