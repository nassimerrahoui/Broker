package components;

import java.util.ArrayList;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import interfaces.PublicationI;
import interfaces.ReceptionI;

@RequiredInterfaces(required = {ReceptionI.class})
@OfferedInterfaces(offered = {PublicationI.class})
public class Courtier extends AbstractComponent{

	public Courtier(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		// TODO Auto-generated constructor stub
	}
	
	protected ArrayList<Message> messages;
	
	public void publierMessage(Message m) {
		messages.add(m);
	}

}
