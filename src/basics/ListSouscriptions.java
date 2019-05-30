package basics;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * La classe ListSouscriptions est un objet qui represente les souscriptions stockes sous forme de map.
 * souscriptions : key<uriInBoundConsommateur> x value<Map<Nom_du_Topic,Souscription>> : 
 * A chaque InBoundPort de consommateur est associe une map de <topic,souscription>.
 * On utilise une ConcurrentHashMap pour gerer les acces concurrents des differentes souscriptions.
 *
 */
public class ListSouscriptions {

	/**
	 * Represente une liste map de souscriptions, la cle est l'uriInboundPort du consommateur et l valeur est les souscriptions de ce consommateur.
	 */
	private ConcurrentHashMap<String, Vector<Souscription>> souscriptions = new ConcurrentHashMap<String, Vector<Souscription>>();

	private Vector<String> ConsommateurUris = new Vector<String>();
	
	/**
	 * ajoute une souscription au consommateur qui a comme uri de port entrant pour la reception uriInboundConsommateur
	 * @param s la soucription
	 * @param uriInBoundConsommateur l'URI du port entrant du consommateur qui veut souscrire
	 * @throws Exception
	 */
	public void addSouscriptionToConsommateur(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.put(uriInBoundConsommateur, new Vector<Souscription>());
		}
		souscriptions.get(uriInBoundConsommateur).add(s);
		ConsommateurUris.addElement(uriInBoundConsommateur);
	}

	/**
	 * retourne la map des souscriptions
	 */
	public ConcurrentHashMap<String, Vector<Souscription>> getSouscriptions() {
		return souscriptions;
	}
	
	/**
	 * retourne les uri des ports entrant  pour la reception de chaque abonne
	 * @return
	 */
	public Vector<String> getConsommateurUris() {
		return ConsommateurUris;
	}
}
