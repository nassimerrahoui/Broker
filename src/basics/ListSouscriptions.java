package basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.ListSouscriptionsI;
import interfaces.MessageI;
import interfaces.SouscriptionI;

public class ListSouscriptions implements ListSouscriptionsI{

	private Map<String, ArrayList<SouscriptionI>> consommateur_ss = new HashMap<String, ArrayList<SouscriptionI>>();
	

	public void deleteSouscription(SouscriptionI s, String uriConsommateur) throws Exception {
		if (consommateur_ss.containsKey(uriConsommateur)){
			consommateur_ss.get(uriConsommateur).remove(s);
		}
	}
	
	public void addSouscriptionToConsommateur(SouscriptionI s, String uriConsommateur) throws Exception {
		if (!consommateur_ss.containsKey(uriConsommateur)) {
			consommateur_ss.put(uriConsommateur, new ArrayList<SouscriptionI>());
		}
		consommateur_ss.get(uriConsommateur).add(s);
	}
	

	public void modifyFilter(Topic t,Filter f,String uriConsommateur) {
		ArrayList<SouscriptionI> liste_souscription = consommateur_ss.get(uriConsommateur);
		for (SouscriptionI ss : liste_souscription) {
			if (ss.getTopic().equals(t)) {
				ss.setFilter(f);
			}
		}
		
	}
	

}
