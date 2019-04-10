package interfaces;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ReceptionServiceI extends RequiredI, OfferedI {

	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception;

	public void recevoirNMessage(ArrayList<Message> msg, String uriInboundPort) throws Exception;

}
