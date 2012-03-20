package model;

import java.util.ArrayList;

import types.Types;

public class Line extends ArrayList<Types>{

	private static final long serialVersionUID = 1395631029655406828L;
	
	public void outputLine(){
		for(int i=0; i<this.size(); i++){
			System.out.println(this.get(i));
		}
	}
}
