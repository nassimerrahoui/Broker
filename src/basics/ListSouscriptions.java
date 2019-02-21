package basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.ListSouscriptionsI;

public class ListSouscriptions implements ListSouscriptionsI {

	private Map<String, ArrayList<Souscription>> souscriptions = new HashMap<String, ArrayList<Souscription>>();

	public void addSouscriptionToConsommateur(Souscription s, String uriConsommateur) throws Exception {
		if (!souscriptions.containsKey(uriConsommateur)) {
			souscriptions.put(uriConsommateur, new ArrayList<Souscription>());
		}
		souscriptions.get(uriConsommateur).add(s);
	}

	public void deleteSouscription(Souscription s, String uriConsommateur) throws Exception {
		if (souscriptions.containsKey(uriConsommateur)) {
			souscriptions.get(uriConsommateur).remove(s);
		}
	}

	public void modifyFilter(Topic t, Filter f, String uriConsommateur) {
		ArrayList<Souscription> liste_souscription = souscriptions.get(uriConsommateur);
		for (Souscription s : liste_souscription) {
			if (s.topic.equals(t)) {
				s.filter = f;
			}
		}

	}

}
