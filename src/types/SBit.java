package types;

public class SBit extends Types{

	private byte[] val;

	public SBit(){

	}
	
	public SBit(byte[] in){
		this.val = in;
	}

	public String typeToString(){
		return "SByte";
	}
	public byte[] getVal() {
		return val;
	}

	public void setVal(byte[] val) {
		this.val = val;
	}

}
