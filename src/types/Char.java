package types;

public class Char extends Types{
	private char val[];
	private int maxLength;
	
	public Char(char valI[], int length){
		this.val = valI;
		this.maxLength = length;
	}

	public char[] getVal() {
		return val;
	}

	public void setVal(char[] val) {
		this.val = val;
	}
	
	
}
