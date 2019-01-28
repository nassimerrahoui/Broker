package interfaces;

import java.util.ArrayList;
import fr.sorbonne_u.components.interfaces.OfferedI;

public interface ReceptionI extends OfferedI {
	
	public void recevoirMessage(MessageI msg) throws Exception;
	
	public void recevoirNMessage(ArrayList<MessageI> msg) throws Exception;

}
