package test;

import java.util.concurrent.CopyOnWriteArrayList;
import basics.ListTopics;
import basics.Message;

public class Test {

	public static void main(String[] args) throws Exception {
		ListTopics lt = new ListTopics();

		lt.createTopic("fac", "p1");
		lt.createTopic("sport", "p1");
		CopyOnWriteArrayList<String> l = new CopyOnWriteArrayList<String>();
		l.add("maths");
		Message m = new Message("psg bat l'om", "p1", l);
		Message lm = new Message("paris fc bat gdsjhsd fc", "p1", l);
		lt.addMesssageToTopic(m, "p1");
		lt.addMesssageToTopic(lm, "p1");

		System.out.println(lt.existTopicURI("mathfs"));
		lt.deleteTopic("maths", "p2");
		System.out.println(lt.existTopicURI("maths"));
	}

}
