package kr.co.expernet.sandbox.server.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.mapper.CcMapper;
import kr.co.expernet.sandbox.server.mapper.GcsMapper;

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
		try {
			int i = -1;
			while ((i = bis.read()) != -1) {
				BufferedOutputStream cc = CcMapper.get("cc");
				if (cc != null) {
					System.out.print(i);
					cc.write(i);
					cc.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}