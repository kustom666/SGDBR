package controller;

import model.Base;
import model.Table;

public class baseController {
	private Base baseTravail;
	
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
	
	
}

