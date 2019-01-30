package components;

import java.util.ArrayList;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageI;
import interfaces.PublicationI;
import interfaces.ReceptionI;
import ports.CourtierInboundPort;
import ports.CourtierOutboundPort;

@RequiredInterfaces(required = { ReceptionI.class })
@OfferedInterfaces(offered = { PublicationI.class })

public class Courtier extends AbstractComponent {
	
	protected CourtierOutboundPort envoiePort;
	protected CourtierInboundPort publicationPort; //Publication du message à partir du producteur 
	protected ArrayList<MessageI> messages = new ArrayList<MessageI>();
	
	public Courtier(String uri, String portURI) throws Exception {
		super(uri,1,0);
		
		publicationPort = new CourtierInboundPort("inbound-"+portURI,this);
		this.addPort(publicationPort);
		publicationPort.publishPort();
			
		envoiePort = new CourtierOutboundPort("outbound-"+portURI,this);
		this.addPort(envoiePort);
		envoiePort.publishPort();

	}

	public void publierMessage(MessageI m) {
		messages.add(m);
		System.out.println(messages.get(0).getContenu().toString()+ " Ajout du message");
		this.logMessage("Le courtier a recu le message à publier"+ m.getContenu());
	}
	
	public void envoieMessageAndPrint(MessageI msg) throws Exception {
		System.out.println("Envoie du message");
		this.envoiePort.recevoirMessage(msg);
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
	
	@Override
	public void execute() throws Exception {
		super.execute();
		System.out.println("executeeee");
		
		this.envoieMessageAndPrint(messages.get(0));
		/*this.runTask(
				new AbstractTask() {
					public void run() {
						try {
							((Courtier) this.owner).
							//((Courtier)this.getOwner()).envoieMessageAndPrint(((Courtier) this.owner).messages.get(0));
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}) ;*/
		
	}

}
