package kr.co.expernet.sandbox.server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class Client {
	Socket socket;
	@Test
	public void connection() {
		try {
			socket = new Socket("115.91.242.69", 18120);
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
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void connect() {
		Socket socket;
		BufferedInputStream bis = null;
		try {
			socket = new Socket("61.38.108.231", 51000);
			bis = new BufferedInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void concurrentHashMapTest() {
		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
		BufferedOutputStream bos = new BufferedOutputStream(System.out);
		map.put("1", bos);
		assertTrue(map.remove("1", new BufferedOutputStream(System.out)));
		
		map.put("1", bos);
		assertFalse(map.remove("1", new BufferedOutputStream(System.out)));
	}

}
