package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationI extends RequiredI, OfferedI {
	
	public MessageI publierMessage(MessageI m) throws Exception;
	public MessageI [] publierNMessage(int numberOfMsg,MessageI m);
	
}
