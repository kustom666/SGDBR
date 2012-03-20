package types;

public class Float extends Types{
	private float val;
	
	public Float(float valInc){
		this.val = valInc;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
	
	public String toString(){
		Float buff = new Float(this.val);
		return buff.toString();
	}
}
