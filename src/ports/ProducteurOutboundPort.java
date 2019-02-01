package ports;

import java.util.ArrayList;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ListTopicsI;
import interfaces.MessageI;
import interfaces.PublicationI;

public class ProducteurOutboundPort extends AbstractOutboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public ProducteurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert uri != null && owner != null;
	}
	
	public void publierMessage(MessageI msg) throws Exception {
		((PublicationI) this.connector).publierMessage(msg);
	}

	public void publierNMessage(ArrayList<MessageI> msgs) throws Exception {
		((PublicationI) this.connector).publierNMessage(msgs);
	}
	
	public void createTopic(String uri, String uriProducteur) throws Exception {
		((ListTopicsI) this.connector).createTopic(uri, uriProducteur);
		
	}

	public void deleteTopic(String uri, String uriProd) throws Exception {
		((ListTopicsI) this.connector).deleteTopic(uri, uriProd);
	}

	public Boolean existTopicURI(String uri) throws Exception {
		return ((ListTopicsI) this.connector).existTopicURI(uri);
	}

	public ArrayList<String> getUriTopics() throws Exception {
		return ((ListTopicsI) this.connector).getUriTopics();
	}

}
