package ports;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Message;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.PublicationI;

public class CourtierInboundPort extends AbstractInboundPort implements PublicationI {

	private static final long serialVersionUID = 1L;

	public CourtierInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);

		assert uri != null & owner != null;
	}

	public void publierMessage(final Message msg) throws Exception {

		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).publierMessage(msg);
				return null;
			}
		});

	}

	public void publierNMessage(final CopyOnWriteArrayList<Message> msgs) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).publierNMessages(msgs);
				return null;
			}
		});
	}

	public void createTopic(final String uri, final String uriProducteur) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).createTopic(uri);
				return null;
			}
		});
	}

	public void deleteTopic(final String uri) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).deleteTopic(uri);
				return null;
			}
		});
	}

}
