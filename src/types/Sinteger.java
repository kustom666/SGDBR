package types;

public class Sinteger extends Types {
	private int valeur;
	
	public Sinteger(){
		
	}
	
	public Sinteger(int val){
		this.valeur = val;
	}
	
	public String toString(){
		return Integer.toString(this.valeur);
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public String typeToString(){
		return "Integer";
	}
	
}
