package model;

import java.util.ArrayList;

import types.Types;

public class Line extends ArrayList<Types>{

	private static final long serialVersionUID = 1395631029655406828L;
	
	public void outputLine(ArrayList<Column> arrColCorres){
		for(int i=0; i<this.size(); i++){

			System.out.print("|");
			for(int j=0; j<(arrColCorres.get(i).getLabel().length()+arrColCorres.get(i).getType().typeToString().length()-this.get(i).toString().length())/2;j++){
				System.out.print(" ");
			}
			System.out.print(" "+this.get(i).toString()+ " ");
			
			for(int j=0; j<(arrColCorres.get(i).getLabel().length()+arrColCorres.get(i).getType().typeToString().length()-this.get(i).toString().length())/2;j++){
				System.out.print(" ");
			}
			
		}
		System.out.print("|");
		System.out.println("");
	}
}
