package kr.co.expernet.sandbox.server.handler;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOHandler {
	private static final Logger log = LoggerFactory.getLogger(IOHandler.class);
	
	public static void close(Closeable... closeable) {
		for (Closeable c : closeable) {
			try {
				c.close();
				log.info("--- {} IS CLOSED.", c.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
