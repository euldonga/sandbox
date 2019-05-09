package kr.co.expernet.sandbox.server.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.enums.Client;
import kr.co.expernet.sandbox.server.handler.IOHandler;
import kr.co.expernet.sandbox.server.mapper.CcMapper;
import kr.co.expernet.sandbox.server.mapper.GcsMapper;

public class GcsStreamer implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(GcsStreamer.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private final String process;
	private final String key = Client.GCS_VIDEO.getName();
	
	public GcsStreamer(Socket socket) {
		process = Thread.currentThread().getName();
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			IOHandler.regist(GcsMapper.class, key, bis, bos);
			log.info("--- {} --- {} CONNECTED.", process, key);
		} catch (Exception e) {
			e.printStackTrace();
			IOHandler.close(bis, bos);
		}
	}

	@Override
	public void run() {
		relayStream();
	}
	
	private void relayStream() {
		try {
			int len = 0;
			byte[] buffer = new byte[4096];
			while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
				BufferedOutputStream cc = CcMapper.getBos(Client.CC_VIDEO.getName());
				if (cc != null) {
					cc.write(buffer, 0, len);
					cc.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				IOHandler.close(GcsMapper.class, key, bis, bos);
				log.info("--- {} --- GCS VIDEO STREAMER TERMINATE.", process);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}