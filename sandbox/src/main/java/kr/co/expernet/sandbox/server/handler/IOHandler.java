package kr.co.expernet.sandbox.server.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;

public class IOHandler {
	private IOHandler() {}
	
	public static void regist(Class<?> mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) throws Exception {
		close(mapper, key);
		remove(mapper, key);
		add(mapper, key, bis, bos);
	}

	public static void close(Class<?> mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) throws Exception {
		remove(mapper, key, bis, bos);
		close(bis, bos);
	}

	public static void close(Closeable... closeable) {
		for (Closeable c : closeable) {
			try {
				if (c != null) {
					c.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(Class<?> mapper, String key) throws Exception {
		close(getBis(mapper, key), getBos(mapper, key));
	}

	private static void add(Class<?> mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) throws Exception {
		mapper.getDeclaredMethod("add", String.class, BufferedInputStream.class, BufferedOutputStream.class).invoke(null, key, bis, bos);
	}

	private static BufferedInputStream getBis(Class<?> mapper, String key) throws Exception {
		return (BufferedInputStream) mapper.getDeclaredMethod("getBis", String.class).invoke(null, key);
	}

	private static BufferedOutputStream getBos(Class<?> mapper, String key) throws Exception {
		return (BufferedOutputStream) mapper.getDeclaredMethod("getBos", String.class).invoke(null, key);
	}
	
	private static void remove(Class<?> mapper, String key) throws Exception {
		mapper.getDeclaredMethod("remove", String.class).invoke(null, key);
	}

	private static void remove(Class<?> mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) throws Exception {
		mapper.getDeclaredMethod("remove", String.class, BufferedInputStream.class, BufferedOutputStream.class).invoke(null, key, bis, bos);
	}

}
