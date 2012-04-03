package model;

import types.Types;

public class Couple {
	private String nom = new String();
	private Types typeC;
	
	public Couple(String n, Types t){
		this.nom = n;
		this.typeC = t;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Types getTypeC() {
		return typeC;
	}
	public void setTypeC(Types typeC) {
		this.typeC = typeC;
	}
}
