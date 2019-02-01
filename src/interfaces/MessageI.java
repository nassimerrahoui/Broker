package interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface MessageI extends Serializable {
	
	public ArrayList<String> getTopicsURI();
	public String getIDMessage();
	public long getDatePublication();
	public String getIDDateur();
	public Serializable getContenu();
	public String getUriProducteur();

}

