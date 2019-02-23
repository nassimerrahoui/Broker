package interfaces;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationI extends RequiredI, OfferedI {

	public void publierMessage(Message m) throws Exception;

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception;

}
