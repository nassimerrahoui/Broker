package app;

import components.Consommateur;
import components.Courtier;
import components.Producteur;
import connectors.PublicationServiceConnector;
import connectors.ReceptionServiceConnector;
import connectors.SouscriptionServiceConnector;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import interfaces.PublicationServiceI;
import interfaces.ReceptionServiceI;
import interfaces.SouscriptionServiceI;

public class CVM extends AbstractCVM {

	protected Consommateur consommateur;
	protected Producteur producteur;
	protected Courtier courtier;
	protected Courtier courtier1;
	protected Courtier courtier2;
	protected Courtier courtier3;

	public CVM() throws Exception {
		super();
	}

	@Override
	public void deploy() throws Exception {

		assert !this.deploymentDone();

		// --------------------------------------------------------------------
		// Creation phase
		// --------------------------------------------------------------------

		this.producteur = new Producteur();
		this.courtier = new Courtier("outTransfertC1","inTransfertC1",1);
		this.consommateur = new Consommateur();
		this.deployedComponents.add(producteur);
		this.deployedComponents.add(courtier);
		this.deployedComponents.add(consommateur);

		// --------------------------------------------------------------------
		// Connection phase
		// --------------------------------------------------------------------
		
		// Connexion entre producteur et courtier

		this.producteur.doPortConnection(producteur.findOutboundPortURIsFromInterface(PublicationServiceI.class)[0],
				courtier.findInboundPortURIsFromInterface(PublicationServiceI.class)[0],
				PublicationServiceConnector.class.getCanonicalName());
		
		// Connexion entre courtier et consommateur
		
		this.courtier.doPortConnection(courtier.findOutboundPortURIsFromInterface(ReceptionServiceI.class)[0],
				consommateur.findInboundPortURIsFromInterface(ReceptionServiceI.class)[0],
				ReceptionServiceConnector.class.getCanonicalName());
		
		// Connexion entre consommateur et courtier 
		
		this.consommateur.doPortConnection(consommateur.findOutboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
				courtier.findInboundPortURIsFromInterface(SouscriptionServiceI.class)[0],
				SouscriptionServiceConnector.class.getCanonicalName());
		
		// --------------------------------------------------------------------
		// Deployment done
		// --------------------------------------------------------------------

		super.deploy();
		assert this.deploymentDone();
	}

	@Override
	public void shutdown() throws Exception {
		assert this.allFinalised();
		
		// print logs on files, if activated
//		this.consommateur.toggleLogging();
//		this.courtier.toggleLogging();
//		this.producteur.toggleLogging();
//		this.consommateur.printExecutionLogOnFile("CVM_Consommateur");
//		this.producteur.printExecutionLogOnFile("CVM_Producteur");
//		this.courtier.printExecutionLogOnFile("CVM_Courtier");
		super.shutdown();
	}

	public static void main(String[] args) {
		try {
			// Create an instance of the defined component virtual machine.
			CVM a = new CVM();
			// Execute the application.
			a.startStandardLifeCycle(15000L);
			// Give some time to see the traces (convenience).
			Thread.sleep(10000L);
			// Simplifies the termination (termination has yet to be treated
			// properly in BCM).
			System.exit(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}