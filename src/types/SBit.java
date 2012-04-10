package types;

public class SBit extends Types{

	private byte[] val;

	public SBit(){

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
