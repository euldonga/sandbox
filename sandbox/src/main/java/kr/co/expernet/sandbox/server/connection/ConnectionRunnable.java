package kr.co.expernet.sandbox.server.connection;

import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionRunnable implements Runnable {
	public Class<?> type;
	private int port;

	public ConnectionRunnable(Class<?> type, int port) {
		this.type = type;
		this.port = port;
	}

	@SuppressWarnings("unused")
	private ConnectionRunnable() {}
	
	@Override
	public void run() {
		try (ServerSocket server = new ServerSocket(port)) {
			while (true) {
				start(server.accept());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start(Socket socket) {
		try {
			Constructor<? extends Object> con = type.getDeclaredConstructor(Socket.class);
			Runnable r = (Runnable) con.newInstance(socket);
			Thread thread = new Thread(r);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}