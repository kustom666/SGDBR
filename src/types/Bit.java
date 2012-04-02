package types;

public class Bit extends Types{

	private byte[] val;

	public Bit(){

	}

	public String typeToString(){
		return "Byte";
	}
	public byte[] getVal() {
		return val;
	}

	public void setVal(byte[] val) {
		this.val = val;
	}

}
