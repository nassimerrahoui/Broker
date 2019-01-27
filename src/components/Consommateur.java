package components;

import fr.sorbonne_u.components.AbstractComponent;
import interfaces.ReceptionI;

public class Consommateur extends AbstractComponent implements ReceptionI {

	public Consommateur(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);

	}

	public void recevoirMessage() {
		// TODO Auto-generated method stub
		
	}

	public void recevoirNMessage() {
		// TODO Auto-generated method stub
		
	}

	

}
