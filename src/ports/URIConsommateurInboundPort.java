package ports;

import java.util.ArrayList;
import components.Consommateur;
import components.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ComponentI.ComponentService;
import fr.sorbonne_u.components.examples.basic_cs.components.URIProvider;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ReceptionI;

public class URIConsommateurInboundPort extends AbstractInboundPort implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public URIConsommateurInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert uri != null && owner instanceof Consommateur;
	}

	public void recevoirMessage(String uri, Message msg) throws Exception {
//		this.getOwner().handleRequestAsync(
//				
//				new AbstractComponent.AbstractService<Void>() {
//					@Override					
//					public Void call() throws Exception {
//						
//					}
//				}
//				
//				);
				
	}

	public void recevoirNMessage(String uri, ArrayList<Message> msg) {

	}

}