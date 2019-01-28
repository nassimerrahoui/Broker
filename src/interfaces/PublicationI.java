package interfaces;

import components.Message;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationI extends RequiredI{
	
	public MessageI publierMessage(MessageI m) throws Exception;
	public MessageI [] publierNMessage(int numberOfMsg,MessageI m);
	
}
