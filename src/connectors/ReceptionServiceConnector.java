package connectors;

import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ReceptionServiceI;

/**
 * 
 * Classe representant le connecteur pour le service de Reception entre le port entrant du
 * Consommateur et sortant du Courtier.
 *
 */
public class ReceptionServiceConnector extends AbstractConnector implements ReceptionServiceI {
	/**
	 * Appelle la methode de Reception de message au sein du port entrant du Consommateur. 
	 */

	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception {
		((ReceptionServiceI) this.offering).recevoirMessage(msg, uriInboundPort);
	}
}
