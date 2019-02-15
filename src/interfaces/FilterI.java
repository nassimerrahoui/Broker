package interfaces;

import java.util.ArrayList;

public interface FilterI {
	public boolean its_a_match(MessageI msg);
	public boolean its_all_match(ArrayList<MessageI> msgs);
}
