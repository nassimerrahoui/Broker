package ports;

import components.Message;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageI;
import interfaces.PublicationI;

public class URICourtierInboundPort extends AbstractInboundPort
implements PublicationI{

	public URICourtierInboundPort(Class<?> implementedInterface, ComponentI owner) throws Exception {
		super(implementedInterface, owner);	
	}

	public MessageI publierMessage(final MessageI m) throws Exception {
		return this.getOwner().handleRequestSync(
				new AbstractComponent.AbstractService<MessageI>() {
								
					public MessageI call() throws Exception {
						return ((PublicationI)this.getOwner()).publierMessage(m);
						
					}
				}
				
				);
		
	}

	public MessageI[] publierNMessage(int numberOfMsg,MessageI m) {
		// TODO Auto-generated method stub
		return null;
	}

}
