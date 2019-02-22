package basics;

import interfaces.FilterI;
import interfaces.TopicI;

public class Souscription {
	
	//idSouscription pour retrouver sa souscription

	public TopicI topic;
	public FilterI filter;
	public String uriInboundReception;

	public Souscription(TopicI t, FilterI filter,String uri) {
		this.topic = t;
		this.filter = filter;
		this.uriInboundReception = uri;
	}

}
