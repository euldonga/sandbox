package kr.co.expernet.sandbox.server.mapper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public interface Mapper {
	public void add(String key, BufferedInputStream bis, BufferedOutputStream bos);

	public BufferedInputStream getBis(String key);

	public BufferedOutputStream getBos(String key);

	public void remove(String key, BufferedInputStream bis, BufferedOutputStream bos);

	public void remove(String key);
}
