package connectors;

import java.util.ArrayList;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.MessageI;
import interfaces.ReceptionI;

public class ReceptionConnector extends AbstractConnector implements ReceptionI {

	public void recevoirMessage(MessageI msg) throws Exception {
		((ReceptionI) this.requiring).recevoirMessage(msg);
	}

	public void recevoirNMessage(ArrayList<MessageI> msgs) throws Exception {
		((ReceptionI) this.requiring).recevoirNMessage(msgs);
	}

}
