package components;

import java.util.ArrayList;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;

@RequiredInterfaces(required = { ReceptionI.class })
@OfferedInterfaces(offered = { PublicationI.class })

public class Courtier extends AbstractComponent {

	public Courtier(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
	}

	protected ArrayList<MessageI> messages;

	public void publierMessage(MessageI m) {
		messages.add(m);
	}

}
