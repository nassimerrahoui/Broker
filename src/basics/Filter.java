package basics;

import java.io.Serializable;

/**
 * Classe representant le filtre.
 *
 */
public class Filter {

	/** temps systeme unix de la publication du message */
	protected long datePublication;

	/** charge utile serialisable du message */
	protected Serializable contenu;

	/**
	 * Constructeur du  Filtre prenant le critère de date de publication et le contenu.
	 * @param datePublication
	 * @param contenu
	 */
	public Filter(long datePublication, Serializable contenu) {
		this.datePublication = datePublication;
		this.contenu = contenu;
	}

	/**
	 * Constructeur d'un filtre vierge. 
	 */
	public Filter() {
		this.datePublication = 0;
		this.contenu = null;
	}

	/**
	 * Retourne si le message msg satisfait les connditions du filtre ou non.
	 * @param msg
	 * @return
	 */
	public boolean its_a_match(Message msg) {
		boolean matching = true;
		if (datePublication == 0 && contenu == null)
			return matching;
		if (datePublication != 0 && contenu == null) {
			if (msg.getDatePublication() >= datePublication)
				return matching;
		} else if (datePublication == 0 && contenu != null) {
			if (msg.getContenu() instanceof String && contenu instanceof String) {
				if (((String) msg.getContenu()).toLowerCase().contains(((String) contenu).toLowerCase())) {
					System.out.println(" msg contenu = "+ msg.getContenu());
					return matching;
				} else {
					matching = false;
				}

			}
		} else if (datePublication != 0 && contenu != null) {
			if ((msg.getDatePublication() >= datePublication)
					&& ((String) msg.getContenu()).toLowerCase().contains(((String) contenu).toLowerCase())) {
				return matching;
			} else {
				matching = false;
			}
		}
		return matching;
	}

	/**
	 *  Modifie la condition sur la date de publication 
	 * @param datePublication
	 */

	public void setDatePublication(long datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * Modifie la condition sur le contenu
	 * @param contenu
	 */
	public void setContenu(Serializable contenu) {
		this.contenu = contenu;
	}

}
