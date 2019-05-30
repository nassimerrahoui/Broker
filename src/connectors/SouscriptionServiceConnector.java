package connectors;

import basics.Souscription;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.SouscriptionServiceI;

/**
 * 
 * Classe representant le connecteur du service de souscription entre le port entrant du
 * Courtier et sortant du Consommateur.
 *
 */
public class SouscriptionServiceConnector extends AbstractConnector implements SouscriptionServiceI {
	
	/**
	 * Appelle la methode de souscription au sein du port entrant du Courtier.
	 */
	public void souscrire(Souscription s) throws Exception {
		((SouscriptionServiceI) this.offering).souscrire(s);
	}
}
