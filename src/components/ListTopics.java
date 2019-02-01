package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import interfaces.ListTopicsI;
import interfaces.MessageI;

public class ListTopics implements ListTopicsI {

	private Map<Topic, ArrayList<MessageI>> topic_messages = new HashMap<Topic, ArrayList<MessageI>>();

	public void createTopic(String uri, String uriProducteur) {
		Topic t = new Topic(uri, uriProducteur);
		topic_messages.put(t, new ArrayList<MessageI>());
	}

	public void deleteTopic(String uri, String uriProd) {
		Topic t = getTopicByUri(uri);
		if (t.getProducteurURI().equals(uriProd)) {
			topic_messages.remove(t);
			System.out.println("Votre topic a ete supprime");
			
		}

	}

	public Boolean existTopicURI(String uri) {
		return getUriTopics().contains(uri);
	}

	public ArrayList<String> getUriTopics() {
		ArrayList<Topic> lm = new ArrayList<Topic>(topic_messages.keySet());
		ArrayList<String> list = new ArrayList<String>();  
		for (Topic topic : lm) {
			list.add(topic.getTopicURI());
		}
		return list;
	}

	public Topic getTopicByUri(String uri) {
		Topic topic = null;
		for (Topic t : topic_messages.keySet()) {
			if (t.getTopicURI().equals(uri)) {
				return t;
			}
		}
		return topic;
	}

	public void addMesssageToTopic(MessageI msg, String uriProducteur) {

		for (String uri : msg.getTopicsURI()) {

			if (!topic_messages.containsKey(this.getTopicByUri(uri))) {
				Topic t = new Topic(uri, uriProducteur);
				topic_messages.put(t, new ArrayList<MessageI>());
			}

			topic_messages.get(getTopicByUri(uri)).add(msg);

		}
	}
	
	public void addNMesssageToTopic(ArrayList<MessageI> msgs , String uriProducteur) {

		for (MessageI m : msgs) {
			addMesssageToTopic(m,uriProducteur);
		}
	}
	
	public ArrayList<MessageI> getMessagesByUriTopic(String uri){
		return topic_messages.get(getTopicByUri(uri));
	}

}
