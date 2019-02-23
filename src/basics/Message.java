package basics;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.net.InetAddress;

public class Message {

	private static final long serialVersionUID = 1L;
	/** identifiant unique du message */
	protected final String uri;
	/** temps systeme unix de la publication du message */
	protected final long datePublication;
	/** identifiant du dateur de la publication */
	protected final String idDateur;
	/** charge utile serialisable du message */
	protected final Serializable contenu;
	/** le sujet du message */
	protected final CopyOnWriteArrayList<String> uriTopics;
	/** le URI du producteur du message */
	protected final String uriProducteur;

	public Message(Serializable contenu, String idPublieur, CopyOnWriteArrayList<String> uriTopics) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		this.uri = java.util.UUID.randomUUID().toString();
		this.datePublication = System.currentTimeMillis();
		this.idDateur = inetAddress.getHostName();
		this.uriProducteur = idPublieur;
		this.contenu = contenu;
		this.uriTopics = new CopyOnWriteArrayList<String>(uriTopics);
	}

	public String getIDMessage() {
		return uri;
	}

	public long getDatePublication() {
		return datePublication;
	}

	public String getIDDateur() {
		return idDateur;
	}

	public String getUriProducteur() {
		return uriProducteur;
	}

	public Serializable getContenu() {
		return contenu;
	}

	@Override
	public String toString() {
		return contenu.toString() + " - publie par " + idDateur + ", date: " + datePublication;
	}

	public CopyOnWriteArrayList<String> getTopicsURI() {
		return uriTopics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
