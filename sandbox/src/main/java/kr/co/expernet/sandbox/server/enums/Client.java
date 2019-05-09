package kr.co.expernet.sandbox.server.enums;

public enum Client {
	CC(1, "CC"), GCS(2, "GCS"), CC_VIDEO(10, "CC_STREAMER"), GCS_VIDEO(11, "GCS_STREAMER");

	private int code;
	private String name;

	private Client(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
