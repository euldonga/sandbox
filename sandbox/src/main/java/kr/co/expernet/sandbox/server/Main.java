package kr.co.expernet.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.connection.ConnectionRunnable;
import kr.co.expernet.sandbox.server.receiver.CcReceiver;
import kr.co.expernet.sandbox.server.receiver.GcsReceiver;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		log.info("-----------------------< START SANDBOX RELAY SERVER >-----------------------");
		Runnable cc = new ConnectionRunnable(CcReceiver.class, 19110);
		Thread ccReceiver = new Thread(cc);
		ccReceiver.start();
		Runnable gcs = new ConnectionRunnable(GcsReceiver.class, 19120);
		Thread gcsReceiver = new Thread(gcs);
		gcsReceiver.start();
	}

}
