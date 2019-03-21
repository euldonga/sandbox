package kr.co.expernet.sandbox.server.mapper;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GcsMapper {
	private static Map<String, BufferedOutputStream> gcs = new HashMap<>();

	public static void add(String key, BufferedOutputStream stream) {
		gcs.put(key, stream);
	}

	public static BufferedOutputStream get(String key) {
		return gcs.get(key);
	}

	public static void remove(String key) {
		gcs.remove(key);
	}
}
