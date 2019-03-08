package basics;

public class Souscription {

	public String topic;
	public Filter filter;
	public String uriInboundReception;

	public Souscription(String t, Filter filter, String uriInBoundConsommateur) {
		this.topic = t;
		this.filter = filter;
		this.uriInboundReception = uriInBoundConsommateur;
	}

}
