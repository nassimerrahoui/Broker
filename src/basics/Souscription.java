package basics;

public class Souscription {

	public Topic topic;
	public Filter filter;
	public String uriInboundReception;

	public Souscription(Topic t, Filter filter, String uri) {
		this.topic = t;
		this.filter = filter;
		this.uriInboundReception = uri;
	}

}
