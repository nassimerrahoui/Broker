package interfaces;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Message;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ReceptionI extends OfferedI, RequiredI {

	public void recevoirMessage(Message msg) throws Exception;

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msg) throws Exception;

}
