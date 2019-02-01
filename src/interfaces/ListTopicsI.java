package interfaces;

import java.util.ArrayList;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ListTopicsI extends OfferedI, RequiredI {
	
	public void createTopic(String name, String uriProducteur) throws Exception;
	public void deleteTopic(String uri, String uriProd) throws Exception;
	public Boolean existTopicURI(String uri) throws Exception;
	public ArrayList<String> getUriTopics() throws Exception;
}
