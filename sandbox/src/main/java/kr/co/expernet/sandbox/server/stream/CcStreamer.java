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

public class CcStreamer implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(CcStreamer.class);
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public CcStreamer(Socket socket) {
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());
			CcMapper.add("ccstream", bos);
			log.info("--- CC STREAMER CONNECTED.");
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
				BufferedOutputStream gcs = GcsMapper.get("gcsstream");
				if (gcs != null) {
					gcs.write(buffer, 0, len);
					gcs.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOHandler.close(bis, bos);
			log.info("--- CC STREAMER TERMINATE.");
		}
	}
}