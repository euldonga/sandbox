package kr.co.expernet.sandbox.server.mapper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CcMapper {
	private static ConcurrentHashMap<String, BufferedOutputStream> out = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<String, BufferedInputStream> in = new ConcurrentHashMap<>();

	public static void add(String key, BufferedInputStream bis, BufferedOutputStream bos) {
		in.put(key, bis);
		out.put(key, bos);
	}

	public static BufferedInputStream getBis(String key) {
		return in.get(key);
	}

	public static BufferedOutputStream getBos(String key) {
		return out.get(key);
	}

	public static void remove(String key, BufferedInputStream bis, BufferedOutputStream bos) {
		if (in.containsKey(key) && Objects.equals(in.get(key), bis)) {
			in.remove(key, bis);
		}
		if (out.containsKey(key) && Objects.equals(out.get(key), bos)) {
			out.remove(key, bos);
		}
	}

	public static void remove(String key) {
		if (in.containsKey(key)) {
			in.remove(key);
		}
		if (out.containsKey(key)) {
			out.remove(key);
		}
	}
}
