package ports;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Filter;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.MessageServiceI;

public class MessageServiceOutboundPort extends AbstractOutboundPort implements MessageServiceI {

	public MessageServiceOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, MessageServiceI.class, owner);
		assert uri != null && owner != null;
	}

	private static final long serialVersionUID = 1L;

	public void createTopic(String uri) throws Exception {
		((MessageServiceI) this.connector).createTopic(uri);
	}

	public void deleteTopic(String uri) throws Exception {
		((MessageServiceI) this.connector).deleteTopic(uri);
	}
	
	public void publierMessage(Message msg) throws Exception {
		((MessageServiceI) this.connector).publierMessage(msg);
	}

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((MessageServiceI) this.connector).publierNMessage(msgs);
	}

	public void recevoirMessage(Message msg) throws Exception {
		((MessageServiceI) this.connector).recevoirMessage(msg);
	}

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msg) throws Exception {
		((MessageServiceI) this.connector).recevoirNMessage(msg);
	}

	public void resiliation(Topic t,  String uriConsommateur) throws Exception {
		((MessageServiceI) this.connector).resiliation(t,  uriConsommateur);
	}

	public void souscrire(Souscription s) throws Exception {
		((MessageServiceI) this.connector).souscrire(s);
	}

	public void setFilter(Topic t, Filter filter, String uriInBoundConsommateur) throws Exception {
		((MessageServiceI) this.connector).setFilter(t, filter, uriInBoundConsommateur);
	}

}
