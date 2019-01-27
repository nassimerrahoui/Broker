package components;

import java.io.Serializable;
import fr.sorbonne_u.components.AbstractComponent;
import interfaces.MessageI;

public class Message extends AbstractComponent implements MessageI, Serializable {

	private static final long serialVersionUID = 1L;
	String uri; // id du message
	long datePublication; // temps unix
	String producteurName;

	public Message(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);
	}

}
