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
	private final String key = Client.CC.getName();
	
	public CcReceiver(Socket socket) {
		init(socket);
	}
	
	@SuppressWarnings("unused")
	private CcReceiver() { }
	
	@Override
	public void run() {
		log.info("[{}] --- [{}] CONNECTED.", Thread.currentThread().getName(), key);
		relayPacket();
	}
	
	private void init(Socket socket) {
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			IOHandler.regist(CcMapper.class, key, bis, bos);
		} catch (Exception e) {
			e.printStackTrace();
			IOHandler.close(bis, bos);
		}
	}
	
	private void relayPacket() {
		try {
			int len = 0;
			byte[] buffer = new byte[Mavlink.SIZE];
			while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
				BufferedOutputStream gcs = GcsMapper.getBos(Client.GCS.getName());
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
				log.info("[{}] --- [{}] TERMINATE.", Thread.currentThread().getName(), key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}