package types;

public class Integer extends Types {
	private boolean autoIncrement;
	private int val;
	
	public Integer(int valInc){
		this.val = valInc;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
}
