package basics;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 
 * La classe ListTopics est un objet qui représente les messages stockés sous forme map topic_messages.
 * topic_messages : key<Topic> x value<Queue<Messages>> : A chaque topic est associé une liste de messages. 
 * On utilise une ConcurrentHashMap pour topic_messages et une ConcurrentLinkedQueue pour chaque liste de messages
 * afin de gérer les accés concurrent des différentes publications.
 *   
 */
public class ListTopics {

	private ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>> topic_messages = new ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>>();

	public void createTopic(String uri) {
		Topic t = new Topic(uri);
		topic_messages.put(t, new ConcurrentLinkedQueue<Message>());
	}

	public void deleteTopic(String uri) {
		Topic t = getTopicByUri(uri);
		topic_messages.remove(t);
		System.out.println("Votre topic a ete supprime");

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

	public void addMesssageToTopic(Message msg) {

		for (String uri : msg.getTopicsURI()) {

			if (!topic_messages.containsKey(this.getTopicByUri(uri))) {
				Topic t = new Topic(uri);
				topic_messages.put(t, new ConcurrentLinkedQueue<Message>());
			}

			topic_messages.get(getTopicByUri(uri)).add(msg);

		}
	}

	public void addNMesssageToTopic(ArrayList<Message> msgs) {

		for (Message m : msgs) {
			addMesssageToTopic(m);
		}
	}

	public ConcurrentLinkedQueue<Message> getMessagesByUriTopic(String uri) {
		return topic_messages.get(getTopicByUri(uri));
	}
	
	public ConcurrentHashMap<Topic, ConcurrentLinkedQueue<Message>> getTopics(){
		return topic_messages;
	}
}
