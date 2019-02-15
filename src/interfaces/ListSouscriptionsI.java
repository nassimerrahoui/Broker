package interfaces;

public interface ListSouscriptionsI {

	public void addSouscriptionToConsommateur(SouscriptionI s, String uriConsommateur) throws Exception ;
	public void deleteSouscription(SouscriptionI s, String uriConsommateur) throws Exception;
}
