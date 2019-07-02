package kr.co.expernet.sandbox.server.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.expernet.sandbox.server.enums.Client;
import kr.co.expernet.sandbox.server.handler.IOHandler;
import kr.co.expernet.sandbox.server.mapper.CcMapper;

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
		sendFile();
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
	
	private void sendFile() {
		try {
			while (true) {
				System.out.println("connected...");
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			IOHandler.close(bis, bos);
		}
		
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(new File("/expernet/audio/tts.wav"));
//			int readCount = 0;
//			byte[] buffer = new byte[1024];
//			while ((readCount = fis.read(buffer)) != -1) {
//				bos.write(buffer, 0, readCount);
//				bos.flush();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			IOHandler.close(fis, bis, bos);
//		}
	}
	
	private void sendPcm() {
		AudioInputStream ais = null;
		AudioInputStream pcm = null;
		try {
			ais = AudioSystem.getAudioInputStream(new File("/expernet/audio/tts.mp3"));
			AudioFormat format = ais.getFormat();
			AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000, 16,
					format.getChannels(), format.getChannels() * 2, 8000, false);
			pcm = AudioSystem.getAudioInputStream(decodedFormat, ais);
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = pcm.read(buffer)) != -1) {
				bos.write(buffer, 0, readCount);
				bos.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOHandler.close(ais, pcm, bis, bos);
		}
	}
}