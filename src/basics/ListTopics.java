package basics;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * La classe ListTopics est un objet qui repr�sente les messages stock�s sous forme map topic_messages.
 * topic_messages : key : Topic x value : vector : Messages : A chaque topic est associ� une liste de messages. 
 * On utilise une ConcurrentHashMap pour topic_messages et un vector pour chaque liste de messages
 * afin de g�rer les acc�s concurrent des diff�rentes publications.
 *   
 */
public class ListTopics {

	private ConcurrentHashMap<Topic, Vector<Message>> topic_messages = new ConcurrentHashMap<Topic, Vector<Message>>();

	/**
	 * Cr�e un topic avec l'uri
	 * @param uri
	 */
	public void createTopic(String uri) {
		
			Topic t = new Topic(uri);
			topic_messages.put(t, new Vector<Message>());
		
	}

	/**
	 * Supprime un topic de la map
	 * @param uri
	 */
	public void deleteTopic(String uri) {
		Topic t = getTopicByUri(uri);
		topic_messages.remove(t);
		System.out.println("Votre topic a ete supprime");

	}

	/**
	 * Retourne si un topic existe.
	 * @param uri
	 * @return
	 */
	public Boolean existTopicURI(String uri) {
		return getUriTopics().contains(uri);
	}

	/**
	 * Retourne les uris de tout les topics de la map
	 * @return
	 */
	public Vector<String> getUriTopics() {
		Vector<Topic> lm = new Vector<Topic>(topic_messages.keySet());
		Vector<String> list = new Vector<String>();
		for (Topic topic : lm) {
			list.add(topic.getTopicURI());
		}
		return list;
	}

	/**
	 * Retourne un topic avec l'uri.
	 * @param uri
	 * @return
	 */
	public Topic getTopicByUri(String uri) {
		Topic topic = null;
		for (Topic t : topic_messages.keySet()) {
			if (t.getTopicURI().equals(uri)) {
				return t;
			}
		}
		return topic;
	}

	/**
	 * Ajoute un message � un topic
	 * @param msg
	 */
	public void addMesssageToTopic(Message msg) {

		for (String uri : msg.getTopicsURI()) {

			if (!topic_messages.containsKey(this.getTopicByUri(uri))) {
				Topic t = new Topic(uri);
				topic_messages.put(t, new Vector<Message>());
			}

			topic_messages.get(getTopicByUri(uri)).add(msg);

		}
	}

	/**
	 * Ajoute N messages � leurs topics respectifs.
	 * @param msgs
	 */
	public void addNMesssageToTopic(ArrayList<Message> msgs) {

		for (Message m : msgs) {
			addMesssageToTopic(m);
		}
	}
	
	/**
	 * Retourne les messages ayant l'uri "uri"
	 * @param uri
	 * @return
	 */
	public Vector<Message> getMessagesByUriTopic(String uri) {
		return topic_messages.get(getTopicByUri(uri));
	}
	
	/**
	 * Retourne la map topic_messages.
	 * @return
	 */
	public ConcurrentHashMap<Topic, Vector<Message>> getTopicsMessagesMap(){
		return topic_messages;
	}
}
