package types;

public class Sinteger extends Types {
	private int valeur;
	
	public Sinteger(int val){
		this.valeur = val;
	}
	
	public String toString(){
		return Integer.toString(this.valeur);
	}
}
