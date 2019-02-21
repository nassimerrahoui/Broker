package basics;

import interfaces.FilterI;
import interfaces.TopicI;

public class Souscription {

	public TopicI topic;
	public FilterI filter;
	public String uriConsommateur;

	public Souscription(TopicI t, FilterI filter) {
		this.topic = t;
		this.filter = filter;
	}

}
