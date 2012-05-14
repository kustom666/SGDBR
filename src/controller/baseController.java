package controller;

import java.util.ArrayList;

import model.Base;
import model.Table;
/**
 * baseController, le controlleur permettant de gérer la base dans son ensemble
 * 
 * @author Paul Forti
 * 
 * */
public class baseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375778026458326716L;
	
	private Base baseTravail=new Base("default");
	
	/**
	 * Le constructeur par défaut construit une base sans nom de base et l'alloue au contrôleur
	 * @author Paul Forti
	 * */
	public baseController(){
		
	}
	/**
	 * Le constructeur de controlleur de base permettant de prendre une base déjà existante et de l'allouer au contrôleur 
	 * @author Paul Forti
	 * */
	public baseController(Base b){
		this.baseTravail = b;
	}
	/**
	 * La méthode initialise permet de créer une nouvelle base à partir d'un simple nom de base et de l'allouer au contrôleur
	 * @param nomBase : le nom de la base à créer
	 * @author Paul Forti
	 * */
	public void initialise(String nomBase){
		Base buffBase = new Base(nomBase);
		this.baseTravail = buffBase;
	}
	/**
	 * La méthode ajouterTable permet d'ajouter une table à la base actuellement contrôlée 
	 * @param  t la table à ajouter
	 * @author Paul Forti
	 * */
	public void ajouterTable(Table t){
		this.baseTravail.add(t);
	}

	public Base getBaseTravail() {
		return baseTravail;
	}

	public void setBaseTravail(Base baseTravail) {
		this.baseTravail = baseTravail;
	}
	
	/**
	 * La méthode saveBaseCSV permet de sauvegarder toutes les tables contenues dans la base actuellement contrôlée.
	 * @author Paul Forti
	 * */
	public void saveBaseCSV(){
		for(int i=0;i<this.baseTravail.size(); i++){
			baseTravail.get(i).saveTable(baseTravail.getNom()+baseTravail.get(i).getTableName());
		}
	}
}

