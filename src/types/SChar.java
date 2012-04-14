package types;

public class SChar extends Types{
	private char val[];
	private int maxLength;

	public SChar(){

	}

	public SChar(char valI[], int length){
		this.val = valI;
		this.maxLength = length;
	}

	public String typeToString(){
		return "Char";
	}

	public char[] getVal() {
		return val;
	}

	public void setVal(char[] val) {
		this.val = val;
	}


}
