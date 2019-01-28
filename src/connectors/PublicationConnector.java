package connectors;

import java.util.ArrayList;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.MessageI;
import interfaces.PublicationI;

public class PublicationConnector extends AbstractConnector implements PublicationI {

	public void publierMessage(MessageI msg) throws Exception {
		((PublicationI) this.offering).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception {
		((PublicationI) this.offering).publierNMessage(msgs);
	}

}
