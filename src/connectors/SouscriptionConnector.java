package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.FilterI;
import interfaces.SouscriptionI;
import interfaces.TopicI;

public class SouscriptionConnector extends AbstractConnector implements SouscriptionI {

	public void souscrire(TopicI t, FilterI filter) {
		((SouscriptionI)this.offering).souscrire(t, filter);
	}

	public void resiliation(TopicI t) {
		((SouscriptionI)this.offering).resiliation(t);

	}

	public FilterI getFilter() {
		return ((SouscriptionI)this.offering).getFilter();
	}

	public void setFilter(FilterI filter) {
		((SouscriptionI)this.offering).setFilter(filter);

	}

	public TopicI getTopic() {
		return ((SouscriptionI)this.offering).getTopic();
	}

}
