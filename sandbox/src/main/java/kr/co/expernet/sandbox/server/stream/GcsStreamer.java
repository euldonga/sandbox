package kr.co.expernet.sandbox.server.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.handler.IOHandler;
import kr.co.expernet.sandbox.server.mapper.CcMapper;
import kr.co.expernet.sandbox.server.mapper.GcsMapper;

public class GcsStreamer implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(GcsStreamer.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public GcsStreamer(Socket socket) {
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			GcsMapper.add("gcsstream", bos);
			log.info("--- GCS STREAMER CONNECTED.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			int len = 0;
			byte[] buffer = new byte[4096];
			while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
				BufferedOutputStream cc = CcMapper.get("ccstream");
				if (cc != null) {
					cc.write(buffer, 0, len);
					cc.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOHandler.close(bis, bos);
			log.info("--- GCS STREAMER TERMINATE.");
		}
	}
}