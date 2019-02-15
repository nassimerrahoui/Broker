package basics;

import interfaces.FilterI;
import interfaces.SouscriptionI;
import interfaces.TopicI;

public class Souscription implements SouscriptionI {
	
	TopicI topic;
	FilterI filter;
	
	public Souscription(TopicI t, FilterI filter) {
		this.topic = t;
		this.filter = filter;
	}

	public void souscrire(TopicI t, FilterI filter) {
		
	}

	public void resiliation(TopicI t) {
		// TODO Auto-generated method stub
		
	}
	public FilterI getFilter() {
		return filter;
	}
	public void setFilter(FilterI filter) {
		this.filter = filter;
	}
	
	public TopicI getTopic() {
		return topic;
	}

}
