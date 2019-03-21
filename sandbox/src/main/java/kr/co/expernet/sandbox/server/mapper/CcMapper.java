package kr.co.expernet.sandbox.server.mapper;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CcMapper {
	private static Map<String, BufferedOutputStream> cc = new HashMap<>();

	public static void add(String key, BufferedOutputStream stream) {
		cc.put(key, stream);
	}

	public static BufferedOutputStream get(String key) {
		return cc.get(key);
	}

	public static void remove(String key) {
		cc.remove(key);
	}
}
