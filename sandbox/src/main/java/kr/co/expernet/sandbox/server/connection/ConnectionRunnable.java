package kr.co.expernet.sandbox.server.connection;

import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionRunnable implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ConnectionRunnable.class);
	public Class<?> type;
	private int port;
	private ServerSocket serverSocket;

	public ConnectionRunnable(Class<?> type, int port) {
		this.type = type;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			log.info("--- CREATE SERVER SOCKET {} PORT", port);
			while (true) {
				Socket socket = serverSocket.accept();
				Constructor<? extends Object> con = type.getDeclaredConstructor(Socket.class);
				Runnable r = (Runnable) con.newInstance(socket);
				Thread thread = new Thread(r);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
