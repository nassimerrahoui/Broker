package ports;

import java.util.ArrayList;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;

public class CourtierInboundPort extends AbstractInboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public CourtierInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri,PublicationI.class, owner);
		
		assert uri != null & owner != null;
	}

	public void publierMessage(MessageI msg) throws Exception {
		((PublicationI) this.owner).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception {
		((PublicationI) this.owner).publierNMessage(msgs);
	}

}
