package connectors;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ReceptionI;

public class ReceptionConnector extends AbstractConnector implements ReceptionI {

	public void recevoirMessage(Message msg) throws Exception {
		((ReceptionI) this.offering).recevoirMessage(msg);
	}

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((ReceptionI) this.offering).recevoirNMessage(msgs);
	}

}
