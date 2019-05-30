package connectors;

import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.PublicationServiceI;

/**
 * Classe representant le connecteur  pour le service de Publication entre le port entrant du courtier 
 * et sortant du Producteur.
 * 
 * @author Skander
 *
 */
public class PublicationServiceConnector extends AbstractConnector implements PublicationServiceI {

	/**
	 * Appelle la methode de creation de topic au sein du  port entrant du Courtier. 
	 */
	public void createTopic(String uri) throws Exception {
		((PublicationServiceI) this.offering).createTopic(uri);
	}

	/**
	 * Appelle la methode de publication au sein du  port entrant du  courtier
	 */
	public void publierMessage(Message msg) throws Exception {
		((PublicationServiceI) this.offering).publierMessage(msg);
	}

	/**
	 * Appelle la methode de transfert au sein du  port entrant du  courtier.
	 */
	@Override
	public void transfererMessage(Message msg) throws Exception {
		((PublicationServiceI) this.offering).transfererMessage(msg);
		
	}
}
