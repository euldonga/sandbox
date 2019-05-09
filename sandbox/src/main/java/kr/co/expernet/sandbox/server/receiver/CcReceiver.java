package kr.co.expernet.sandbox.server.receiver;

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
import kr.co.expernet.sandbox.server.protocol.Mavlink;

public class CcReceiver implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(CcReceiver.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private final String process;
	private final String key = Client.CC.getName();
	
	public CcReceiver(Socket socket) {
		process = Thread.currentThread().getName();
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			IOHandler.regist(CcMapper.class, key, bis, bos);
			log.info("--- {} --- {} CONNECTED.", process, key);
		} catch (Exception e) {
			e.printStackTrace();
			IOHandler.close(bis, bos);
		}
	}
	
	@Override
	public void run() {
		relayPacket();
	}
	
	private void relayPacket() {
		try {
			int len = 0;
			byte[] buffer = new byte[Mavlink.SIZE];
			while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
				BufferedOutputStream gcs = GcsMapper.getBos("gcs");
				if (gcs != null) {
					gcs.write(buffer, 0, len);
					gcs.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				IOHandler.close(CcMapper.class, key, bis, bos);
				log.info("--- {} --- GCS RECEIVER TERMINATE.", process);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}