package connectors;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Filter;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.MessageServiceI;

public class MessageServiceConnector extends AbstractConnector implements MessageServiceI {

	public void publierMessage(Message msg) throws Exception {
		((MessageServiceI) this.offering).publierMessage(msg);
	}

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((MessageServiceI) this.offering).publierNMessage(msgs);
	}

	public void createTopic(String uri) throws Exception {
		((MessageServiceI) this.offering).createTopic(uri);
	}

	public void deleteTopic(String uri) throws Exception {
		((MessageServiceI) this.offering).deleteTopic(uri);
	}

	public void recevoirMessage(Message msg,String uriInboundPort) throws Exception {
		if (this.getOfferingPortURI() == uriInboundPort) {
			((MessageServiceI) this.offering).recevoirMessage(msg,uriInboundPort);
		}
		
	}

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((MessageServiceI) this.offering).recevoirNMessage(msgs);
	}

	public void souscrire(Souscription s) throws Exception {
		((MessageServiceI) this.offering).souscrire(s);
	}

	public void resiliation(Topic t, String uriInBoundConsommateur) throws Exception {
		((MessageServiceI) this.offering).resiliation(t, uriInBoundConsommateur);
	}

	public void setFilter(Topic t, Filter filter, String uriInBoundConsommateur) throws Exception {
		((MessageServiceI) this.offering).setFilter(t, filter, uriInBoundConsommateur);
	}

}
