package ports;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.ListTopics;
import basics.Message;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PublicationI;

public class ProducteurOutboundPort extends AbstractOutboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public ProducteurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}

	public void publierMessage(Message msg) throws Exception {
		((PublicationI) this.connector).publierMessage(msg);
	}

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((PublicationI) this.connector).publierNMessage(msgs);
	}

	public void createTopic(String uri) throws Exception {
		((ListTopics) this.connector).createTopic(uri);

	}

	public void deleteTopic(String uri, String uriProd) throws Exception {
		((ListTopics) this.connector).deleteTopic(uri);
	}

	public Boolean existTopicURI(String uri) throws Exception {
		return ((ListTopics) this.connector).existTopicURI(uri);
	}

	public CopyOnWriteArrayList<String> getUriTopics() throws Exception {
		return ((ListTopics) this.connector).getUriTopics();
	}

}
