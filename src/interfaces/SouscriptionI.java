package interfaces;

import basics.Souscription;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SouscriptionI extends RequiredI, OfferedI {

	public void souscrire(Souscription s);

	public void resiliation(TopicI t, String uriConsommateur);

	public void setFilter(FilterI filter);
}
