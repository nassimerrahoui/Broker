package interfaces;

import java.util.concurrent.CopyOnWriteArrayList;

import basics.Filter;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface MessageServiceI extends RequiredI, OfferedI {

	public void publierMessage(Message m) throws Exception;

	public void publierNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception;

	public void createTopic(String uri) throws Exception;

	public void deleteTopic(String uri) throws Exception;
	
	public void recevoirMessage(Message msg, String uriInboundPort) throws Exception;

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msg) throws Exception;
	
	public void souscrire(Souscription s) throws Exception;

	public void resiliation(Topic t, String uriInBoundConsommateur) throws Exception;

	public void setFilter(Topic t, Filter filter, String uriInBoundConsommateur) throws Exception;

}
