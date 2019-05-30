package interfaces;

import basics.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ReceptionServiceI extends RequiredI, OfferedI {

	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception;
}
