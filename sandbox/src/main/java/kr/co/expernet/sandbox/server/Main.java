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
//		log.info("-----------------------< START AUDIO RELAY SERVER >-----------------------");
//		new Thread(new ConnectionRunnable(CcReceiver.class, 51000)).start();
//		new Thread(new ConnectionRunnable(GcsReceiver.class, 52000)).start();
		
		
		
		log.info("-----------------------< START MAVLINK RELAY SERVER >-----------------------");
//		new Thread(new ConnectionRunnable(CcReceiver.class, 21000)).start();
//		new Thread(new ConnectionRunnable(GcsReceiver.class, 22000)).start();
		new Thread(new ConnectionRunnable(CcReceiver.class, 21001)).start();
		new Thread(new ConnectionRunnable(GcsReceiver.class, 22001)).start();
//		new Thread(new ConnectionRunnable(CcReceiver.class, 21002)).start();
//		new Thread(new ConnectionRunnable(GcsReceiver.class, 22002)).start();
		log.info("-----------------------< START VIDEO STREAMING SERVER >-----------------------");
//		new Thread(new ConnectionRunnable(CcStreamer.class, 41000)).start();
//		new Thread(new ConnectionRunnable(GcsStreamer.class, 42000)).start();
		new Thread(new ConnectionRunnable(CcStreamer.class, 41001)).start();
		new Thread(new ConnectionRunnable(GcsStreamer.class, 42001)).start();
//		new Thread(new ConnectionRunnable(CcStreamer.class, 41002)).start();
//		new Thread(new ConnectionRunnable(GcsStreamer.class, 42002)).start();
	}
}
