package components;

import java.util.concurrent.TimeUnit;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.examples.basic_cs.components.URIConsumer;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageI;
import interfaces.PublicationI;
import ports.URIProducteurOutboundPort;

@RequiredInterfaces(required = {PublicationI.class})
public class Producteur extends AbstractComponent {
	
	protected final static int	N = 2 ;
	
	
	protected URIProducteurOutboundPort publicationPort;
	
	protected int counter;

	public Producteur(String uri, String outboundPortURI) 
	throws Exception{
		super(uri,0,1);
		
		publicationPort = new URIProducteurOutboundPort(outboundPortURI, this);
		this.addPort(publicationPort);
		publicationPort.localPublishPort();
		this.counter = 0;
		
		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir")) ;
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home")) ;
		}
		this.tracer.setTitle("producteur") ;
		this.tracer.setRelativePosition(1, 1) ;
	}

	public void publishMessageAndPrint() {
		this.counter++;
		if(counter <= 10) {
			//publier un message et l'afficher
			MessageI msg = this.publicationPort.publierMessage();
			this.logMessage("producteur publie un nouveau msg no "+this.counter
					+ ": "+msg+".");
			
			MessageI [] msgs = this.publicationPort.publierNMessage(Producteur.N);
			StringBuffer mes = new StringBuffer() ;
			for(int i=0; i < Producteur.N; i++) {
				mes.append(msgs[i]);
				if( i < Producteur.N - 1) {
					mes.append(", ") ;
				}
			}
			
			this.logMessage("producteur publie un nouvel ensemble de messages no "
					+ this.counter + " [" + mes + "].") ;
			
			
			 this.scheduleTask(
					new AbstractComponent.AbstractTask() {
						public void run() {
							try {
								((Producteur)this.getOwner()).
													publishMessageAndPrint() ;
							} catch (Exception e) {
								throw new RuntimeException(e) ;
							}
						}
					},
					1000, TimeUnit.MILLISECONDS) ;

			 
		}
		
	}
	
	@Override
	public void start() throws ComponentStartException {
		super.start();
		this.logMessage("starting producteur component");
		
		this.scheduleTask(
				new AbstractComponent.AbstractTask() {
					public void run() {
						try {
							((Producteur)this.getOwner()).
												publishMessageAndPrint() ;
						} catch (Exception e) {
							throw new RuntimeException(e) ;
						}
					}
				},
				1000, TimeUnit.MILLISECONDS) ;
	}
	
	@Override
	public void finalise() throws Exception {
		this.logMessage("stopping producteur component.") ;
		
		this.publicationPort.doDisconnection();
		this.publicationPort.unpublishPort();
		
		super.finalise();
	}


}
