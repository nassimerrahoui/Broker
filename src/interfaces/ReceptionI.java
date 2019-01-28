package interfaces;

import java.util.ArrayList;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ReceptionI extends OfferedI, RequiredI {

	public void recevoirMessage(MessageI msg) throws Exception;

	public void recevoirNMessage(ArrayList<MessageI> msg) throws Exception;

}
