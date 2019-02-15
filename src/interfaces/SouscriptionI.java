package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SouscriptionI extends RequiredI, OfferedI {
	
	public void souscrire(TopicI t, FilterI filter);
	public void resiliation(TopicI t);
}
