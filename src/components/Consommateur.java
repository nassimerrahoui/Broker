package components;

import fr.sorbonne_u.components.AbstractComponent;
import interfaces.ReceptionI;

public class Consommateur extends AbstractComponent implements ReceptionI {

	public Consommateur(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);

	}

	@Override
	public void recevoirMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recevoirNMessage() {
		// TODO Auto-generated method stub
		
	}

}
