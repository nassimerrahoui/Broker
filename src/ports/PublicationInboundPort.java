package ports;

import java.util.ArrayList;
import basics.Message;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.PublicationServiceI;

public class PublicationInboundPort extends AbstractInboundPort implements PublicationServiceI {

	private static final long serialVersionUID = 1L;

	public PublicationInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationServiceI.class, owner);
		assert uri != null;
	}

	public void createTopic(final String uri) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).createTopic(uri);
				return null;
			}
		});
	}

	public void publierMessage(final Message msg) throws Exception {

		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).publierMessage(msg);
				return null;
			}
		});

	}

	public void publierNMessage(final ArrayList<Message> msgs) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).publierNMessages(msgs);
				return null;
			}
		});
	}
}