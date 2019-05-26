package basics;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * La classe ListSouscriptions est un objet qui repr�sente les souscriptions stock�s sous forme de map.
 * souscriptions : key<uriInBoundConsommateur> x value<Map<Nom_du_Topic,Souscription>> : 
 * A chaque InBoundPort de consommateur est associ� une map de <topic,souscription>.
 * On utilise une ConcurrentHashMap pour g�rer les acc�s concurrents des diff�rentes souscriptions.
 *
 */
public class ListSouscriptions {

	private ConcurrentHashMap<String, Vector<Souscription>> souscriptions = new ConcurrentHashMap<String, Vector<Souscription>>();

	private Vector<String> ConsommateurUris = new Vector<String>();
	
	public void addSouscriptionToConsommateur(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.put(uriInBoundConsommateur, new Vector<Souscription>());
		}
		souscriptions.get(uriInBoundConsommateur).add(s);
		ConsommateurUris.addElement(uriInBoundConsommateur);
	}

	public void deleteSouscription(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.get(uriInBoundConsommateur).remove(s);
		}
		ConsommateurUris.remove(uriInBoundConsommateur);
	}

	public void modifyFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		for (Souscription s : souscriptions.get(uriInBoundConsommateur)) {
			if(s.topic.equals(t.uri)) s.filter = f;
		}
	}

	public ConcurrentHashMap<String, Vector<Souscription>> getSouscriptions() {
		return souscriptions;
	}
	
	public Vector<String> getConsommateurUris() {
		return ConsommateurUris;
	}
}
