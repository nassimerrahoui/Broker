package basics;

import java.util.concurrent.ConcurrentHashMap;

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
			souscriptions.get(uriInBoundConsommateur).remove(s);
		}
	}

	public void modifyFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		souscriptions.get(uriInBoundConsommateur).get(t.uri).filter = f;
	}

	public ConcurrentHashMap<String, ConcurrentHashMap<String, Souscription>> getSouscriptions() {
		return souscriptions;
	}

}
