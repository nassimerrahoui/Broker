package basics;

public class Topic {

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
