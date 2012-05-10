package model;

import java.util.ArrayList;

import types.Types;

public class Line extends ArrayList<Types>{

	private static final long serialVersionUID = 1395631029655406828L;

	public void outputLine(){
		for(int i=0; i<this.size(); i++){
			System.out.print("| "+this.get(i).toString()+ " ");
		}
	}
	/**
	 * outputLine est une m�thode permettant de repr�senter les lignes de la table en texte sur la sortie syst�me
	 * @param arrColCorres : l'array list des descriptifs de colonne correspondant aux lignes, pour effectuer les calculs
	 * @author Paul Forti
	 * */
	public void outputLine(ArrayList<Column> arrColCorres){
		for(int i=0; i<this.size(); i++){

			//On initialise une variable valeurDeLigne pour plus de claret� dans les if et les for
			int valeurDeLigne = (arrColCorres.get(i).getLabel().length()+arrColCorres.get(i).getType().typeToString().length()-this.get(i).toString().length())/2;
			//Et une variable tailleDuDesc qui contiens la taille exacte du descriptif
			int tailleDuDesc = arrColCorres.get(i).getType().typeToString().length();
			//Et enfin une qui contiens la taille de la valeur dans la variable stock�e
			int tailleVar = this.get(i).toString().length();

			System.out.print("|");
			if((valeurDeLigne)%2+1 != 0){
				for(int j=0; j<valeurDeLigne+1;j++){
					System.out.print(" ");
				}
			}
			else
			{
				for(int j=0; j<valeurDeLigne;j++){
					System.out.print(" ");
				}
			}
			System.out.print(" "+this.get(i).toString()+ " ");


			if(valeurDeLigne+1%2 != 0){
				if(tailleDuDesc%2 !=0)
				{
					for(int j=0; j<valeurDeLigne;j++){
						System.out.print(" ");
					}
				}
				else
				{
					for(int j=0; j<valeurDeLigne+1;j++){
						System.out.print(" ");
					}
				}

			}
			else
			{
				if(tailleVar%2 !=0)
				{
					for(int j=0; j<valeurDeLigne+1;j++){
						System.out.print(" ");
					}
				}
				else
				{
					for(int j=0; j<valeurDeLigne+1;j++){
						System.out.print(" ");
					}
				}
			}

		}
		System.out.print("|");
		System.out.println("");
	}
	
	public String outputLineToString(){
		//Nouvelle méthode, il nous faut un string à renvoyer 
		String buffout = new String("");
		
		for(int i=0; i<this.size(); i++){
			buffout+=";"+this.get(i).toString();
		}
		buffout+="|\n";
		return buffout;
	}
	
}
