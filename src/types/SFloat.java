package types;

/**
 * SBit, la classe héritant de Types permettant de stocker un float en mémoire
 * @author Paul Forti
 * */
public class SFloat extends Types{
	private float val;

	public SFloat(){

	}
	public SFloat(float valInc){
		this.val = valInc;
	}

	public String toString(){
		Float buff = new Float(this.val);
		return buff.toString();
	}

	public String typeToString(){
		return "SFloat";
	}
	public String typeToExport(){
		return "float";
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
}
