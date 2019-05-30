package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import java.net.InetAddress;

/**
 * Classe representant un message
 *
 */
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
	protected final Vector<String> uriTopics = new Vector<String>();

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

	/**
	 * Getter uri de message.
	 * @return
	 */
	public String getIDMessage() {
		return uri;
	}

	/**
	 * Getter date de publication.
	 * @return
	 */
	public long getDatePublication() {
		return datePublication;
	}

	/**
	 * Retourne l'id de publieur.
	 * @return
	 */
	public String getIDDateur() {
		return hostname;
	}

	/**
	 * Retourne le contenu du message
	 * @return
	 */
	public Serializable getContenu() {
		return contenu;
	}

	@Override
	public String toString() {
		return contenu.toString() + " - publie par " + hostname + ", date: " + datePublication;
	}

	/**
	 * Retourne les topics du message.
	 * @return
	 */
	public Vector<String> getTopicsURI() {
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
