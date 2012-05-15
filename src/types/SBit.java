package types;

/**
 * SBit, la classe héritant de Types permettant de stocker un array de bit en mémoire
 * @author Paul Forti
 * */
public class SBit extends Types{

	private byte[] val;

	public SBit(){
	}
	
	public SBit(byte[] in){
		this.val = in;
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
