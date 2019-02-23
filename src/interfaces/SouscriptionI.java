package interfaces;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SouscriptionI extends RequiredI, OfferedI {

	public void souscrire(Souscription s) throws Exception;

	public void resiliation(Topic t, String uriConsommateur);

	public void setFilter(Filter filter);
}
