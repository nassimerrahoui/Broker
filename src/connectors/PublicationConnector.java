package connectors;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.ListTopics;
import basics.Message;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.PublicationI;

public class PublicationConnector extends AbstractConnector implements PublicationI {

	public void publierMessage(Message msg) throws Exception {
		((PublicationI) this.offering).publierMessage(msg);
	}

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {
		((PublicationI) this.offering).publierNMessage(msgs);
	}

	public void createTopic(String uri, String uriProducteur) throws Exception {
		((ListTopics) this.offering).createTopic(uri);

	}

	public void deleteTopic(String uri, String uriProd) throws Exception {
		((ListTopics) this.offering).deleteTopic(uri);
	}

	public Boolean existTopicURI(String uri) throws Exception {
		return ((ListTopics) this.offering).existTopicURI(uri);
	}

	public CopyOnWriteArrayList<String> getUriTopics() throws Exception {
		return ((ListTopics) this.offering).getUriTopics();
	}

}
