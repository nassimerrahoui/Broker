package components;

import java.util.ArrayList;
import fr.sorbonne_u.components.AbstractComponent;
import interfaces.MessageI;

public class Courtier extends AbstractComponent {

	public Courtier(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
	}

	protected ArrayList<MessageI> messages;

	public void publierMessage(MessageI m) {
		messages.add(m);
	}

}
