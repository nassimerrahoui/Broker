package interfaces;

import java.io.Serializable;

public interface MessageI extends Serializable {
	
	public String getIDMessage();
	public long getDatePublication();
	public String getIDDateur();
	public Serializable getContenu();

}

