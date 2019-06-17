package kr.co.expernet.sandbox.server;

public class polymorphismTest {

	public static void main(String args[]) {
		
	}
	
}

class Dad {
	protected static String me = "dad";

	public void printMe() {
		System.out.println(me);
	}
}

class Son extends Dad {
	protected static String me = "son";

	public void doIt() {
		new Son().printMe();
	}
}

interface Mapper {
//	public static void regist(Mapper mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) throws Exception {
//	close(mapper, key);
//	remove(mapper, key);
//	add(mapper, key, bis, bos);
//}
//
//private static void close(Mapper mapper, String key) {
//	close(getBis(mapper, key), getBos(mapper, key));
//}
//
//private static BufferedInputStream getBis(Mapper mapper, String key) {
//	return (BufferedInputStream) mapper.getBis(key);
//}
//
//private static BufferedOutputStream getBos(Mapper mapper, String key) {
//	return (BufferedOutputStream) mapper.getBos(key);
//}
//
//private static void add(Mapper mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) {
//	mapper.add(key, bis, bos);
//}
//
//private static void remove(Mapper mapper, String key) {
//	mapper.remove(key);
//}
//
//private static void remove(Mapper mapper, String key, BufferedInputStream bis, BufferedOutputStream bos) {
//	mapper.remove(key, bis, bos);
//}
}

