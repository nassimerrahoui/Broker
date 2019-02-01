package connectors;

import java.util.ArrayList;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ListTopicsI;
import interfaces.MessageI;
import interfaces.PublicationI;

public class PublicationConnector extends AbstractConnector implements PublicationI, ListTopicsI {

	public void publierMessage(MessageI msg) throws Exception {
		((PublicationI) this.offering).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception {
		((PublicationI) this.offering).publierNMessage(msgs);
	}

	public void createTopic(String uri, String uriProducteur) throws Exception {
		((ListTopicsI) this.offering).createTopic(uri, uriProducteur);
		
	}

	public void deleteTopic(String uri, String uriProd) throws Exception {
		((ListTopicsI) this.offering).deleteTopic(uri, uriProd);
	}

	public Boolean existTopicURI(String uri) throws Exception {
		return ((ListTopicsI) this.offering).existTopicURI(uri);
	}

	public ArrayList<String> getUriTopics() throws Exception {
		return ((ListTopicsI) this.offering).getUriTopics();
	}

}
