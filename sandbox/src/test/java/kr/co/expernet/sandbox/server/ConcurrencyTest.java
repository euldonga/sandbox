package kr.co.expernet.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrencyTest {

	public void 스태틱_메소드_동시실행() {
		try {
			for (int i = 0; i < 10; i++) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						Relay.데이터_중계();
					}
				};
				Thread t = new Thread(r);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ConcurrencyTest ct = new ConcurrencyTest();
		ct.스태틱_메소드_동시실행();
	}
}

class Relay {
	private static Logger log = LoggerFactory.getLogger(Relay.class);

	public static void 데이터_중계() {
		try {
			log.debug("Current Thread: {}", Thread.currentThread().getName());
			Thread.sleep(10000);
			log.debug("Terminate Thread: {}", Thread.currentThread().getName());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
