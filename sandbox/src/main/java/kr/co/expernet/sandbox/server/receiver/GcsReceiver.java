package kr.co.expernet.sandbox.server.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.handler.IOHandler;
import kr.co.expernet.sandbox.server.mapper.CcMapper;
import kr.co.expernet.sandbox.server.mapper.GcsMapper;
import kr.co.expernet.sandbox.server.protocol.Mavlink;

public class GcsReceiver implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(GcsReceiver.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public GcsReceiver(Socket socket) {
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			GcsMapper.add("gcs", bos);
			log.info("--- GCS CONNECTED.");
		} catch (IOException e) {
			e.printStackTrace();
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
				BufferedOutputStream cc = CcMapper.get("cc");
				if (cc != null) {
					cc.write(buffer, 0, len);
					cc.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOHandler.close(bis, bos);
			log.info("--- GCS RECEIVER TERMINATE.");
			GcsMapper.remove("gcs");
		}
	}
}