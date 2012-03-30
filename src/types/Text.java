package types;

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
	public String toString(){
		return this.val;
	}
	
}
