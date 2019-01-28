package interfaces;

import java.util.ArrayList;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface PublicationI extends RequiredI, OfferedI {

	public void publierMessage(MessageI m) throws Exception;

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception;

}
