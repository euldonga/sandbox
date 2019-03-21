package kr.co.expernet.sandbox.server.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.mapper.CcMapper;
import kr.co.expernet.sandbox.server.mapper.GcsMapper;

public class CcReceiver implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(CcReceiver.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public CcReceiver(Socket socket) {
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			CcMapper.add("cc", bos);
			log.info("--- CC CONNECTED.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			int i = -1;
			while ((i = bis.read()) != -1) {
				BufferedOutputStream gcs = GcsMapper.get("gcs");
				if (gcs != null) {
					gcs.write(i);
					gcs.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}