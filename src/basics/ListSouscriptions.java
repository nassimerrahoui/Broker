package basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.ListSouscriptionsI;

public class ListSouscriptions implements ListSouscriptionsI {

	private Map<String, ArrayList<Souscription>> souscriptions = new HashMap<String, ArrayList<Souscription>>();

	public void addSouscriptionToConsommateur(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.put(uriInBoundConsommateur, new ArrayList<Souscription>());
		}
		souscriptions.get(uriInBoundConsommateur).add(s);
	}

	public void deleteSouscription(Souscription s, String uriInBoundConsommateur) throws Exception {
		if (souscriptions.containsKey(uriInBoundConsommateur)) {
			souscriptions.get(uriInBoundConsommateur).remove(s);
		}
	}

	public void modifyFilter(Topic t, Filter f, String uriInBoundConsommateur) {
		ArrayList<Souscription> liste_souscription = souscriptions.get(uriInBoundConsommateur);
		for (Souscription s : liste_souscription) {
			if (s.topic.equals(t)) {
				s.filter = f;
			}
		}

	}

}
