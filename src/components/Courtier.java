package components;

import java.util.ArrayList;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.ports.PortI;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;
import ports.CourtierInboundPort;
import ports.CourtierOutboundPort;

@RequiredInterfaces(required = { ReceptionI.class })
@OfferedInterfaces(offered = { PublicationI.class })

public class Courtier extends AbstractComponent {
	
	public Courtier(String uri, String portURI) throws Exception {
		super(uri,1,0);
		
		PortI p = new CourtierInboundPort("inbound-"+portURI,this);
		this.addPort(p);
		p.publishPort();
		
		PortI p2 = new CourtierOutboundPort("outbound-"+portURI,this);
		this.addPort(p2);
		p2.publishPort();
	}

	protected ArrayList<MessageI> messages;

	public void publierMessage(MessageI m) {
		messages.add(m);
		this.logMessage("Le courtier a recu le message à publier"+ m.getContenu());
	}
	
	@Override
	public void start() throws ComponentStartException {
		super.start();
		
		this.logMessage("Lancement du courtier");
		
		
	}
	
	@Override
	public void			finalise() throws Exception
	{
		this.logMessage("Arret du courtier.") ;
		super.finalise();
	}

}
