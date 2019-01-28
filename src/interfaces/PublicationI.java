package interfaces;

import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationI extends RequiredI{
	
	public MessageI publierMessage();
	public MessageI [] publierNMessage(int numberOfMsg);
	
}
