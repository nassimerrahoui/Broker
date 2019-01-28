package app;

import fr.sorbonne_u.components.cvm.AbstractCVM;

public class CVM extends AbstractCVM {
	
	
	/** Remplacer les URI par ceux de notre contexte **/
	
	/** URI of the provider component (convenience). */
	protected static final String PROVIDER_COMPONENT_URI = "my-URI-provider";
	/** URI of the consumer component (convenience). */
	protected static final String CONSUMER_COMPONENT_URI = "my-URI-consumer";
	/** URI of the provider outbound port (simplifies the connection). */
	protected static final String URIGetterOutboundPortURI = "oport";
	/** URI of the consumer inbound port (simplifies the connection). */
	protected static final String URIProviderInboundPortURI = "iport";

	public CVM() throws Exception {
		super();
	}

	
	@Override
	public void deploy() throws Exception {
		assert !this.deploymentDone();

		
		// --------------------------------------------------------------------
		// Creation phase
		// --------------------------------------------------------------------

		// create the provider component

		// add it to the deployed components

		// create the consumer component
		
		// add it to the deployed components


		// --------------------------------------------------------------------
		// Connection phase
		// --------------------------------------------------------------------

		// do the connection
		

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