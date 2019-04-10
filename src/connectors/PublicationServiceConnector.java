package connectors;

import java.util.ArrayList;
import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.PublicationServiceI;

public class PublicationServiceConnector extends AbstractConnector implements PublicationServiceI {

	public void createTopic(String uri) throws Exception {
		((PublicationServiceI) this.offering).createTopic(uri);
	}

	public void publierMessage(Message msg) throws Exception {
		((PublicationServiceI) this.offering).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<Message> msgs) throws Exception {
		((PublicationServiceI) this.offering).publierNMessage(msgs);
	}
}
