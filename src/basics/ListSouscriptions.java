package basics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListSouscriptions {

	private ConcurrentHashMap<String, CopyOnWriteArrayList<Souscription>> souscriptions = new ConcurrentHashMap<String, CopyOnWriteArrayList<Souscription>>();

	public void addSouscriptionToConsommateur(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.put(uriInBoundConsommateur, new CopyOnWriteArrayList<Souscription>());
		}
		souscriptions.get(uriInBoundConsommateur).add(s);
	}

	public void deleteSouscription(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.get(uriInBoundConsommateur).remove(s);
		}
	}

	public void modifyFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		for (Souscription s : souscriptions.get(uriInBoundConsommateur)) {
			if (s.topic.equals(t)) {
				s.filter = f;
			}
		}

	}

}
