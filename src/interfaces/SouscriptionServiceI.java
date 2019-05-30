package interfaces;

import basics.Souscription;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface SouscriptionServiceI extends RequiredI, OfferedI {

	public void souscrire(Souscription s) throws Exception;
}
