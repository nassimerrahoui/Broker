package components;

import fr.sorbonne_u.components.AbstractComponent;

public class Producteur extends AbstractComponent {

	public Producteur(String reflectionInboundPortURI, int nbThreads, int nbSchedulableThreads) {
		super(reflectionInboundPortURI, nbThreads, nbSchedulableThreads);
	}

}
