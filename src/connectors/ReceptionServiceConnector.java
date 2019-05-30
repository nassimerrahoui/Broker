package connectors;

import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ReceptionServiceI;

public class ReceptionServiceConnector extends AbstractConnector implements ReceptionServiceI {

	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception {
		((ReceptionServiceI) this.offering).recevoirMessage(msg, uriInboundPort);
	}
}
