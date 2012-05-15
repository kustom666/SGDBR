package types;

/**
 * SBit, la classe héritant de Types permettant de stocker un string en mémoire
 * @author Paul Forti
 * */
public class Text extends Types{
	String val = new String();
	
	public Text(){

	}
	public Text(String s){
		this.val = s;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	public String typeToString(){
		return "Text";
	}
	public String typeToExport(){
		return "text";
	}
	public String toString(){
		return this.val;
	}
	
}
