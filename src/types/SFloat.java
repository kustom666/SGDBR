package types;

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
		return "Float";
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
}
