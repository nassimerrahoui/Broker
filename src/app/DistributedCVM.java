package app;

import components.Consommateur;
import components.Courtier;
import components.Producteur;
import connectors.PublicationServiceConnector;
import connectors.SouscriptionServiceConnector;
import fr.sorbonne_u.components.cvm.AbstractDistributedCVM;
import interfaces.PublicationServiceI;
import interfaces.SouscriptionServiceI;

public class DistributedCVM extends AbstractDistributedCVM {
	
	// URI des JVM defini dans le fichier config.xml
	protected static String	COURTIER_1_JVM_URI = "jvm_courtier_1" ;
	protected static String	COURTIER_2_JVM_URI = "jvm_courtier_2" ;
	protected static String	COURTIER_3_JVM_URI = "jvm_courtier_3" ;
	protected static String	COURTIER_4_JVM_URI = "jvm_courtier_4" ;

	// Composants instancie
	protected Courtier uriCourtier_1;
	protected Courtier uriCourtier_2;
	protected Courtier uriCourtier_3;
	protected Courtier uriCourtier_4;
	protected Producteur uriProducteur_1 ;
	protected Producteur uriProducteur_2 ;
	protected Producteur uriProducteur_3 ;
	protected Producteur uriProducteur_4 ;
	protected Consommateur uriConsommateur_1 ;
	protected Consommateur uriConsommateur_2 ;
	protected Consommateur uriConsommateur_3 ;
	protected Consommateur uriConsommateur_4 ;

	public DistributedCVM(String[] args, int xLayout, int yLayout) throws Exception {
		super(args, xLayout, yLayout);
	}
	
	@Override
	public void	initialise() throws Exception {
		super.initialise() ;
	}

	@Override
	public void	instantiateAndPublish() throws Exception {
		
		if (thisJVMURI.equals(COURTIER_1_JVM_URI)) {

			this.uriCourtier_1 = new Courtier();
			this.uriProducteur_1 = new Producteur() ;
			this.uriConsommateur_1 = new Consommateur();
			
			uriCourtier_1.toggleTracing() ;
			uriCourtier_1.toggleLogging() ;
			
			this.addDeployedComponent(uriCourtier_1) ;
			this.addDeployedComponent(uriProducteur_1) ;
			this.addDeployedComponent(uriConsommateur_1) ;

		} else if (thisJVMURI.equals(COURTIER_2_JVM_URI)) {

			this.uriCourtier_2 = new Courtier();
			this.uriProducteur_2 = new Producteur() ;
			this.uriConsommateur_2 = new Consommateur();
			
			uriCourtier_2.toggleTracing() ;
			uriCourtier_2.toggleLogging() ;
			
			this.addDeployedComponent(uriCourtier_2) ;
			this.addDeployedComponent(uriProducteur_2) ;
			this.addDeployedComponent(uriConsommateur_2) ;

		} else if (thisJVMURI.equals(COURTIER_3_JVM_URI)) {
			
			this.uriCourtier_3 = new Courtier();
			this.uriProducteur_3 = new Producteur() ;
			this.uriConsommateur_3 = new Consommateur();
			
			uriCourtier_3.toggleTracing() ;
			uriCourtier_3.toggleLogging() ;
			
			this.addDeployedComponent(uriCourtier_3) ;
			this.addDeployedComponent(uriProducteur_3) ;
			this.addDeployedComponent(uriConsommateur_3) ;
			
		} else if (thisJVMURI.equals(COURTIER_4_JVM_URI)) {
			
			this.uriCourtier_4 = new Courtier();
			this.uriProducteur_4 = new Producteur() ;
			this.uriConsommateur_4 = new Consommateur();
			
			uriCourtier_4.toggleTracing() ;
			uriCourtier_4.toggleLogging() ;
			
			this.addDeployedComponent(uriCourtier_4) ;
			this.addDeployedComponent(uriProducteur_4) ;
			this.addDeployedComponent(uriConsommateur_4) ;
		} else {
			System.out.println("Unknown JVM URI... " + thisJVMURI) ;
		}
		super.instantiateAndPublish();
	}

	
	@Override
	public void	interconnect() throws Exception {

		if (thisJVMURI.equals(COURTIER_1_JVM_URI)) {
			
			this.uriProducteur_1.doPortConnection(
					uriProducteur_1.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_1.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());
			
			this.uriCourtier_1.doPortConnection(
					uriCourtier_1.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_2.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());
			
			this.uriConsommateur_1.doPortConnection(
					uriConsommateur_1.findOutboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					uriCourtier_1.findInboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					SouscriptionServiceConnector.class.getCanonicalName());
			
		} else if (thisJVMURI.equals(COURTIER_2_JVM_URI)) {
			
			this.uriProducteur_2.doPortConnection(
					uriProducteur_2.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_2.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());

			this.uriCourtier_2.doPortConnection(
					uriCourtier_2.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_3.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());
			
			this.uriConsommateur_2.doPortConnection(
					uriConsommateur_2.findOutboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					uriCourtier_2.findInboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					SouscriptionServiceConnector.class.getCanonicalName());

		} else if (thisJVMURI.equals(COURTIER_3_JVM_URI)) {
			
			this.uriProducteur_3.doPortConnection(
					uriProducteur_3.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_3.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());

			this.uriCourtier_3.doPortConnection(
					uriCourtier_3.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_4.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());
			
			this.uriConsommateur_3.doPortConnection(
					uriConsommateur_3.findOutboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					uriCourtier_3.findInboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					SouscriptionServiceConnector.class.getCanonicalName());

		} else if (thisJVMURI.equals(COURTIER_4_JVM_URI)) {
			
			this.uriProducteur_4.doPortConnection(
					uriProducteur_4.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_4.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());

			this.uriCourtier_4.doPortConnection(
					uriCourtier_4.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
					uriCourtier_1.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
					PublicationServiceConnector.class.getCanonicalName());
			
			this.uriConsommateur_4.doPortConnection(
					uriConsommateur_4.findOutboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					uriCourtier_4.findInboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
					SouscriptionServiceConnector.class.getCanonicalName());

		} else {
			System.out.println("Unknown JVM URI... " + thisJVMURI) ;
		}
		super.interconnect();
	}


	@Override
	public void	shutdown() throws Exception {
		super.shutdown();
	}

	public static void main(String[] args) {
		try {
			DistributedCVM da  = new DistributedCVM(args, 2, 5) ;
			da.startStandardLifeCycle(15000L) ;
			Thread.sleep(10000L) ;
			System.exit(0) ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
}
//-----------------------------------------------------------------------------
