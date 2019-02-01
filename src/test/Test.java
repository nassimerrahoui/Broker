package test;

import java.util.ArrayList;
import components.ListTopics;
import components.Message;
import interfaces.MessageI;

public class Test {

	public static void main(String[] args) throws Exception {
		ListTopics lt = new ListTopics();
		
		lt.createTopic("fac", "p1");
		lt.createTopic("sport", "p1");
		ArrayList<String> l = new ArrayList<String>();
		l.add("maths");
		MessageI m = new Message("psg bat l'om", "p1", l);
		MessageI lm = new Message("paris fc bat gdsjhsd fc", "p1", l);
		lt.addMesssageToTopic(m, "p1");
		lt.addMesssageToTopic(lm, "p1");
		
		//System.out.println(lt.getMessagesByUriTopic("maths").get(1).getContenu().toString());
		//System.out.println(lt.getUriTopics());
		System.out.println(lt.existTopicURI("mathfs"));
		lt.deleteTopic("maths", "p2");
		System.out.println(lt.existTopicURI("maths"));
	}

}
