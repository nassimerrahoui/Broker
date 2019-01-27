package components;

import fr.sorbonne_u.components.AbstractComponent;
import interfaces.PublicationI;

public class Producteur extends AbstractComponent implements PublicationI {

	public Producteur(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);
	}

	@Override
	public void publierMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publierNMessage() {
		// TODO Auto-generated method stub
		
	}

}
