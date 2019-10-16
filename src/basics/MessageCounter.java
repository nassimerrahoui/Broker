package basics;

import components.Courtier;

public class MessageCounter implements Runnable {
	
	Courtier courtier;
	String topic_uri;
	
	public MessageCounter(Courtier courtier, String topic_uri) {
		this.courtier = courtier;
		this.topic_uri = topic_uri;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(0,1);
				if(!courtier.isShutdown()) {
					//System.out.println(courtier.getNbMessageInTopic(topic_uri));	
				} else {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
