package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.FilterI;
import interfaces.SouscriptionI;
import interfaces.TopicI;

public class SouscriptionConnector extends AbstractConnector implements SouscriptionI {

	public void souscrire(TopicI t, FilterI filter) {

	}

	public void resiliation(TopicI t) {
		// TODO Auto-generated method stub

	}

	public FilterI getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFilter(FilterI filter) {
		// TODO Auto-generated method stub

	}

	public TopicI getTopic() {
		// TODO Auto-generated method stub
		return null;
	}

}
