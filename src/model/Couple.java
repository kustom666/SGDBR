package model;

import types.Types;

/**
 * La classe couple défini un couple Nom/Type utile lors de la création de colonnes et de tables
 * @author Paul Forti
 * */
public class Couple {
	private String nom = new String();
	private Types typeC;
	
	/**
	 * Le constructeur de couple exige un nom et un type (de colonne)
	 * @param n le nom de colonne
	 * @param t le type de colonne
	 * @author Paul Forti
	 * */
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
