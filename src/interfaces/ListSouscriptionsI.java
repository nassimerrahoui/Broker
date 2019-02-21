package interfaces;

import basics.Souscription;

public interface ListSouscriptionsI {

	public void addSouscriptionToConsommateur(Souscription s, String uriConsommateur) throws Exception ;
	public void deleteSouscription(Souscription s, String uriConsommateur) throws Exception;
}
