package app;

import components.Consommateur;
import components.Courtier;
import components.Producteur;
import connectors.PublicationConnector;
import connectors.ReceptionConnector;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import interfaces.PublicationI;
import interfaces.ReceptionI;

public class CVM extends AbstractCVM {
	
	//
	protected static final String PRODUCTEUR_COMPONENT_URI = "my-URI-PRODUCTEUR";
	protected static final String CONSOMMATEUR_COMPONENT_URI = "my-URI-CONSOMMATEUR";
	protected static final String COURTIER_COMPONENT_URI = "my-URI-COURTIER";

	protected static final String ConsommateurInboundPortURI = "ConsoInboundPort";
	protected static final String ProducteurOutboundPortURI = "ProducteurOutboundPort";
	
	protected static final String CourtierPortURI = "CourtierPort";
	
	protected Consommateur consommateur;
	protected Producteur producteur;
	protected Courtier courtier;

	public CVM() throws Exception {
		super();
	}

	
	@Override
	public void deploy() throws Exception {
		
		assert !this.deploymentDone();

		
		// --------------------------------------------------------------------
		// Creation phase
		// --------------------------------------------------------------------

		this.producteur = new Producteur(PRODUCTEUR_COMPONENT_URI, ProducteurOutboundPortURI);
		this.courtier = new Courtier(COURTIER_COMPONENT_URI);
		this.consommateur = new Consommateur(CONSOMMATEUR_COMPONENT_URI);

		this.deployedComponents.add(consommateur);
		this.deployedComponents.add(producteur);
		this.deployedComponents.add(courtier);
		
		// --------------------------------------------------------------------
		// Connection phase
		// --------------------------------------------------------------------
		// Connexion entre producteur et courtier
		// utiiser port connection a la place de la ref courtier
		this.producteur.doPortConnection(ProducteurOutboundPortURI, courtier.findInboundPortURIsFromInterface(PublicationI.class)[0],
				PublicationConnector.class.getCanonicalName());
		// Connexion entre courtier et consommateur
		this.courtier.doPortConnection(courtier.findOutboundPortURIsFromInterface(ReceptionI.class)[0],ConsommateurInboundPortURI,
				ReceptionConnector.class.getCanonicalName());
		// --------------------------------------------------------------------
		// Deployment done
		// --------------------------------------------------------------------

		super.deploy();
		assert this.deploymentDone();
	}

	
	@Override
	public void shutdown() throws Exception {
		assert this.allFinalised();
		// any disconnection not done yet can be performed here

		// print logs on files, if activated
		this.consommateur.printExecutionLogOnFile("Consommateur") ;
		this.producteur.printExecutionLogOnFile("Producteur") ;
		this.courtier.printExecutionLogOnFile("Courtier") ;
		super.shutdown();
	}

	public static void main(String[] args) {
		try {
			// Create an instance of the defined component virtual machine.
			CVM a = new CVM();
			// Execute the application.
			a.startStandardLifeCycle(15000L);
			// Give some time to see the traces (convenience).
			Thread.sleep(1000L);
			// Simplifies the termination (termination has yet to be treated
			// properly in BCM).
			System.exit(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}