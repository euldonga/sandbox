package kr.co.expernet.sandbox.server.protocol;

public class Mavlink {

	public static final int SIZE = 1024; // Mavlink2 Max Size 280 byte

	private byte stx; 			// 1byte
	private byte len; 			// 1byte
	private byte inc; 			// 1byte
	private byte cmp; 			// 1byte
	private byte seq; 			// 1byte
	private byte sysId; 		// 1byte
	private byte compId; 		// 1byte
	private byte[] msgId; 		// 3byte
	private byte[] payload; 	// 255byte(max)
	private byte[] checksum; 	// 2byte
	private byte[] signature;	// 13 byte

	public Mavlink() {
		msgId = new byte[3];
		payload = new byte[255];
		checksum = new byte[2];
		signature = new byte[13];
	}

	public byte getStx() {
		return stx;
	}

	public void setStx(byte stx) {
		this.stx = stx;
	}

	public byte getLen() {
		return len;
	}

	public void setLen(byte len) {
		this.len = len;
	}

	public byte getInc() {
		return inc;
	}

	public void setInc(byte inc) {
		this.inc = inc;
	}

	public byte getCmp() {
		return cmp;
	}

	public void setCmp(byte cmp) {
		this.cmp = cmp;
	}

	public byte getSeq() {
		return seq;
	}

	public void setSeq(byte seq) {
		this.seq = seq;
	}

	public byte getSysId() {
		return sysId;
	}

	public void setSysId(byte sysId) {
		this.sysId = sysId;
	}

	public byte getCompId() {
		return compId;
	}

	public void setCompId(byte compId) {
		this.compId = compId;
	}

	public byte[] getMsgId() {
		return msgId;
	}

	public void setMsgId(byte[] msgId) {
		this.msgId = msgId;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public byte[] getChecksum() {
		return checksum;
	}

	public void setChecksum(byte[] checksum) {
		this.checksum = checksum;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	
}
