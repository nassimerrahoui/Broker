package basics;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * La classe ListSouscriptions est un objet qui représente les souscriptions stockés sous forme de map.
 * souscriptions : key<uriInBoundConsommateur> x value<Map<Nom_du_Topic,Souscription>> : 
 * A chaque InBoundPort de consommateur est associé une map de <topic,souscription>.
 * On utilise une ConcurrentHashMap pour gérer les accés concurrents des différentes souscriptions.
 *
 */
public class ListSouscriptions {

	private ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>> souscriptions = new ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>>();

	public void addSouscriptionToConsommateur(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.put(uriInBoundConsommateur, new ConcurrentHashMap<String, Souscription>());
		}
		souscriptions.get(uriInBoundConsommateur).put(s.topic, s);
	}

	public void deleteSouscription(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.get(uriInBoundConsommateur).remove(s.topic);
		}
	}

	public void modifyFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.get(uriInBoundConsommateur).get(t.uri).filter = f;
	}

	public ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>> getSouscriptions() {
		return souscriptions;
	}

}
