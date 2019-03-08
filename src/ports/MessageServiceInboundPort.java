package ports;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.Filter;
import basics.Message;
import basics.Souscription;
import basics.Topic;
import components.Consommateur;
import components.Courtier;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageServiceI;

public class MessageServiceInboundPort extends AbstractInboundPort implements MessageServiceI {

	private static final long serialVersionUID = 1L;

	public MessageServiceInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, MessageServiceI.class, owner);
		assert uri != null && owner instanceof Consommateur;
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

	public void createTopic(final String uri) throws Exception {
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

	public void recevoirMessage(final Message msg) throws Exception {

		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Consommateur) this.getOwner()).recevoirMessage(msg);
				return null;
			}
		});

	}

	public void recevoirNMessage(CopyOnWriteArrayList<Message> msgs) throws Exception {

		((MessageServiceI) this.owner).recevoirNMessage(msgs);

	}

	public void souscrire(final Souscription s) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).souscrire(s, pluginURI);
				return null;
			}
		});

	}

	public void resiliation(final Topic t, final String uriConsommateur) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).resiliation(t, uriConsommateur);
				return null;
			}
		});
	}

	public Filter getFilter() {
		// TODO
		return null;
	}

	public void setFilter(final Topic t, final Filter filter, final String uriInBoundConsommateur) throws Exception {
		this.owner.handleRequestAsync(new AbstractComponent.AbstractService<Void>() {

			public Void call() throws Exception {
				((Courtier) this.getOwner()).setFilter(t, filter, uriInBoundConsommateur);
				return null;
			}
		});

	}

	public Topic getTopic() {
		// TODO
		return null;
	}

	public void setFilter(Filter filter) throws Exception {
		// TODO Auto-generated method stub
		
	}

}