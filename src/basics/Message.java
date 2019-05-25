package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.net.InetAddress;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	/** identifiant unique du message */
	protected final String uri;
	/** temps systeme unix de la publication du message */
	protected final long datePublication;
	/** identifiant du dateur de la publication */
	protected final String hostname;
	/** charge utile serialisable du message */
	protected final Serializable contenu;
	/** le sujet du message */
	protected final ArrayList<String> uriTopics = new ArrayList<String>();

	public Message(Serializable contenu, String idPublieur, ArrayList<String> uriTopics) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		this.uri = java.util.UUID.randomUUID().toString();
		this.datePublication = System.currentTimeMillis();
		this.hostname = inetAddress.getHostName();
		this.contenu = contenu;
		this.uriTopics.addAll(uriTopics);
	}

	public Message(Serializable contenu, String idPublieur, String uriTopic) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		this.uri = java.util.UUID.randomUUID().toString();
		this.datePublication = System.currentTimeMillis();
		this.hostname = inetAddress.getHostName();
		this.contenu = contenu;
		this.uriTopics.add(uriTopic);
	}

	public String getIDMessage() {
		return uri;
	}

	public long getDatePublication() {
		return datePublication;
	}

	public String getIDDateur() {
		return hostname;
	}

	public Serializable getContenu() {
		return contenu;
	}

	@Override
	public String toString() {
		return contenu.toString() + " - publie par " + hostname + ", date: " + datePublication;
	}

	public ArrayList<String> getTopicsURI() {
		return uriTopics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Message) {
			return ((Message)o).uri.equals(this.uri);
		}
		return false;
	}

}
