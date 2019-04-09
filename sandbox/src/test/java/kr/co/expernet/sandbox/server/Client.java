package kr.co.expernet.sandbox.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);
	
	@Test
	public void connection() {
		try {
			Socket socket = new Socket("115.91.242.69", 18120);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while(true) {
				line = br.readLine();
				bw.write(line);
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
