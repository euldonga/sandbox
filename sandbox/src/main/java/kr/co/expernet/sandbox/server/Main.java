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
		log.info("-----------------------< START MAVLINK RELAY SERVER >-----------------------");
		new Thread(new ConnectionRunnable(CcReceiver.class, 51000)).start();
		new Thread(new ConnectionRunnable(GcsReceiver.class, 52000)).start();
	}
}
