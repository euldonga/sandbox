package kr.co.expernet.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.connection.ConnectionRunnable;
import kr.co.expernet.sandbox.server.receiver.CcReceiver;
import kr.co.expernet.sandbox.server.receiver.GcsReceiver;
import kr.co.expernet.sandbox.server.stream.CcStreamer;
import kr.co.expernet.sandbox.server.stream.GcsStreamer;

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
		log.info("-----------------------< START SANDBOX STREAMING SERVER >-----------------------");
		Runnable server = new ConnectionRunnable(CcStreamer.class, 18110);
		Thread serverStream = new Thread(server);
		serverStream.start();
		Runnable client = new ConnectionRunnable(GcsStreamer.class, 18120);
		Thread clientStream = new Thread(client);
		clientStream.start();
	}

}
