package ports;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PublicationServiceI;

public class PublicationOutboundPort extends AbstractOutboundPort implements PublicationServiceI {

	public PublicationOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationServiceI.class, owner);
		assert uri != null && owner != null;
	}

	private static final long serialVersionUID = 1L;

	public void createTopic(String uri) throws Exception {
		((PublicationServiceI) this.connector).createTopic(uri);
	}

	public void publierMessage(Message msg) throws Exception {
		((PublicationServiceI) this.connector).publierMessage(msg);
	}

	public void publierNMessages(ArrayList<Message> msgs) throws Exception {
		((PublicationServiceI) this.connector).publierNMessages(msgs);
	}

}
