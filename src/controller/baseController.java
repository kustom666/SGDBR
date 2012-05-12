package controller;

import java.util.ArrayList;

import model.Base;
import model.Table;

public class baseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375778026458326716L;
	
	private Base baseTravail=new Base("default");
	
	public baseController(){
		
	}
	
	public baseController(Base b){
		this.baseTravail = b;
	}
	
	public void initialise(String nomBase){
		Base buffBase = new Base(nomBase);
		this.baseTravail = buffBase;
	}
	
	public void ajouterTable(Table t){
		this.baseTravail.add(t);
	}

	public Base getBaseTravail() {
		return baseTravail;
	}

	public void setBaseTravail(Base baseTravail) {
		this.baseTravail = baseTravail;
	}
	
	public void saveBaseCSV(){
		for(int i=0;i<this.baseTravail.size(); i++){
			baseTravail.get(i).saveTable(baseTravail.getNom()+baseTravail.get(i).getTableName());
		}
	}
}

