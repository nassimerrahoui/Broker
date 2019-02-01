package components;

import interfaces.TopicI;

public class Topic implements TopicI {
	
	protected final String uri;
	
	protected String uriProducteur;
	
	public Topic(String uri, String uriProducteur) {
		this.uri = uri;
		this.uriProducteur = uriProducteur;
	}

	public String getTopicURI() {
		return uri;
	}

	public String getProducteurURI() {
		return uriProducteur;
	}

}
