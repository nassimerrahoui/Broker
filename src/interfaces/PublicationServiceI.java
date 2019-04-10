package interfaces;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationServiceI extends RequiredI, OfferedI {
	
	public void createTopic(String uri) throws Exception;
	
	public void publierMessage(Message m) throws Exception;

	public void publierNMessage(ArrayList<Message> msgs) throws Exception;
}
