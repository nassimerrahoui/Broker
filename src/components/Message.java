package components;

import java.io.Serializable;
import interfaces.MessageI;

public class Message implements MessageI {


	private static final long serialVersionUID = 1L;
	/** identifiant unique du message */
	protected final String idMessage;
	/** temps systeme unix de la publication du message */
	protected final long datePublication;
	/** identifiant du dateur de la publication */
	protected final String idDateur;
	/** charge utile serialisable du message */
	protected final Serializable contenu;
	
	public Message(Serializable contenu, String idPublieur) {
		this.idMessage = java.util.UUID.randomUUID().toString();
		this.datePublication = System.currentTimeMillis();
		this.idDateur = idPublieur;
		this.contenu = contenu;
	}

	
	public String getIDMessage() {
		return idMessage;
	}

	
	public long getDatePublication() {
		return datePublication;
	}

	
	public String getIDDateur() {
		return idDateur;
	}

	
	public Serializable getContenu() {
		return contenu;
	}
	
	@Override 
	public String toString() {
		return contenu.toString() + " - publie par " + idDateur + ", date: "+ datePublication;
	}

}
