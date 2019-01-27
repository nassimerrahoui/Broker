package interfaces;

import java.util.ArrayList;

import components.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;

public interface ReceptionI extends OfferedI {
	
	public void recevoirMessage(String uri, Message msg) throws Exception;
	
	public void recevoirNMessage(String uri, ArrayList<Message> msg) throws Exception;

}
