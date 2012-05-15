package types;

/**
 * SChar, la classe héritant de Types permettant de stocker un array de char en mémoire
 * @author Paul Forti
 * */
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
		return "SChar";
	}

	public String typeToExport(){
		return "char";
	}
	public char[] getVal() {
		return val;
	}

	public void setVal(char[] val) {
		this.val = val;
	}


}
