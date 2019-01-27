package interfaces;

import java.io.Serializable;

public interface MessageI extends Serializable{
	
	String getIDMessage();
	long getDatePublication();
	String getIDDateur();
	Serializable getContenu();

}

