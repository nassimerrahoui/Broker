package basics;

import java.io.Serializable;
import java.util.ArrayList;
import interfaces.FilterI;
import interfaces.MessageI;

public class Filter implements FilterI {

	/** temps systeme unix de la publication du message */
	protected long datePublication;

	/** charge utile serialisable du message */
	protected Serializable contenu;

	public Filter(long datePublication, Serializable contenu) {
		this.datePublication = datePublication;
		this.contenu = contenu;
	}

	public Filter() {
		this.datePublication = 0;
		this.contenu = null;
	}

	public boolean its_a_match(MessageI msg) {
		boolean matching = true;
		if (datePublication == 0 && contenu == null)
			return matching;
		if (datePublication != 0 && contenu == null) {
			if (msg.getDatePublication() >= datePublication)
				return matching;
		} else if (datePublication == 0 && contenu != null) {
			if (msg.getContenu() instanceof String && contenu instanceof String) {
				if (((String) msg.getContenu()).toLowerCase().contains(((String) contenu).toLowerCase())) {
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

	public boolean its_all_match(ArrayList<MessageI> msgs) {
		return false;
	}

	public void setDatePublication(long datePublication) {
		this.datePublication = datePublication;
	}

	public void setContenu(Serializable contenu) {
		this.contenu = contenu;
	}
	

}
