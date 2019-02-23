package basics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTopics {

	private ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>> topic_messages = new ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>>();

	public void createTopic(String uri, String uriProducteur) {
		Topic t = new Topic(uri, uriProducteur);
		topic_messages.put(t, new ConcurrentLinkedQueue<Message>());
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

	public CopyOnWriteArrayList<String> getUriTopics() {
		CopyOnWriteArrayList<Topic> lm = new CopyOnWriteArrayList<Topic>(topic_messages.keySet());
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
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

	public void addMesssageToTopic(Message msg, String uriProducteur) {

		for (String uri : msg.getTopicsURI()) {

			if (!topic_messages.containsKey(this.getTopicByUri(uri))) {
				Topic t = new Topic(uri, uriProducteur);
				topic_messages.put(t, new ConcurrentLinkedQueue<Message>());
			}

			topic_messages.get(getTopicByUri(uri)).add(msg);

		}
	}

	public void addNMesssageToTopic(CopyOnWriteArrayList<Message> msgs, String uriProducteur) {

		for (Message m : msgs) {
			addMesssageToTopic(m, uriProducteur);
		}
	}

	public ConcurrentLinkedQueue<Message> getMessagesByUriTopic(String uri) {
		return topic_messages.get(getTopicByUri(uri));
	}
}
