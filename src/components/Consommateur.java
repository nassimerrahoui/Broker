package components;

import java.util.ArrayList;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.exceptions.InvariantException;
import fr.sorbonne_u.components.exceptions.PostconditionException;
import fr.sorbonne_u.components.exceptions.PreconditionException;
import fr.sorbonne_u.components.ports.PortI;
import interfaces.MessageI;
import interfaces.ReceptionI;
import ports.URIConsommateurInboundPort;

@OfferedInterfaces(offered = { ReceptionI.class })

public class Consommateur extends AbstractComponent {

	protected String uriPrefix;
	protected ArrayList<MessageI> messages = new ArrayList<MessageI>();

	protected static void checkInvariant(Consommateur c) {
		assert c.uriPrefix != null : new InvariantException("Le prefix URI est null!");
		assert c.isOfferedInterface(ReceptionI.class) : new InvariantException("Ce composant doit offrir ReceptionI!");
	}

	public Consommateur(String uriPrefix, String consommateurPortURI) throws Exception {

		super(uriPrefix, 1, 0);

		assert uriPrefix != null : new PreconditionException("uri ne peut etre null!");
		assert consommateurPortURI != null : new PreconditionException("consommateurPortURI ne peut etre null!");

		this.uriPrefix = uriPrefix;

		// port exposant inteface offerte
		PortI p = new URIConsommateurInboundPort(consommateurPortURI, this);
		// ajouter le port dans le set de ports
		this.addPort(p);
		// publier le port
		p.publishPort();

		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir"));
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home"));
		}
		this.tracer.setTitle("consommateur");
		this.tracer.setRelativePosition(1, 0);

		Consommateur.checkInvariant(this);
		assert this.uriPrefix.equals(uriPrefix) : new PostconditionException("URI prefix non initialise!");
		assert this.isPortExisting(consommateurPortURI) : new PostconditionException(
				"Le consommateur doit avoir un port avec URI " + consommateurPortURI);
		assert this.findPortFromURI(consommateurPortURI).getImplementedInterface()
				.equals(ReceptionI.class) : new PostconditionException(
						"Le consommateur doit avoir un port avec une interface ReceptionI implemente");
		assert this.findPortFromURI(consommateurPortURI).isPublished() : new PostconditionException(
				"Le consommateur doit avoir un port publie avec URI " + consommateurPortURI);
	}

	@Override
	public void start() throws ComponentStartException {
		this.logMessage("Lancement du composant Consommateur.");
		super.start();
	}

	@Override
	public void finalise() throws Exception {
		this.logMessage("Arret du composant Consommateur.");
		super.finalise();

	}

	@Override
	public void shutdown() throws ComponentShutdownException {
		try {
			PortI[] p = this.findPortsFromInterface(ReceptionI.class);
			p[0].unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdown();
	}

	@Override
	public void shutdownNow() throws ComponentShutdownException {
		try {
			PortI[] p = this.findPortsFromInterface(ReceptionI.class);
			p[0].unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdownNow();
	}

	public void recevoirMessage(MessageI msg) {
		this.logMessage("consommateur recoit un nouveau msg...");
		assert msg != null : new PostconditionException("msg est vide!");
		messages.add(msg);
	}

	public void recevoirNMessages(ArrayList<MessageI> msgs) throws Exception {
		this.logMessage("consommateur recoit plusieurs msg...");
		assert msgs != null : new PostconditionException("Pas de msg!");

		for (int i = 0; i < msgs.size(); i++) {
			if (msgs.get(i) == null) msgs.remove(i);
		}
		messages.addAll(msgs);
	}
}
