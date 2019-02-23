package ports;

import basics.Filter;
import basics.Souscription;
import basics.Topic;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.SouscriptionI;

public class ConsommateurOutboundPort extends AbstractOutboundPort implements SouscriptionI {

	public ConsommateurOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, SouscriptionI.class, owner);
		assert uri != null && owner != null;
	}

	private static final long serialVersionUID = 1L;

	public void resiliation(Topic t, String uriInPort) {
		((SouscriptionI) this.connector).resiliation(t, uriInPort);

	}

	public void souscrire(Souscription s) throws Exception {
		((SouscriptionI) this.connector).souscrire(s);

	}

	public void setFilter(Filter filter) {
		((SouscriptionI) this.connector).setFilter(filter);

	}

}
