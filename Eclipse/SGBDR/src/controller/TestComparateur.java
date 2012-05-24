package controller;

import java.util.Date;
import java.util.regex.Pattern;
import types.Types;
/**
 * <b> TestComparateur </b> permet la comparaison de valeur 
 * @author tony
 *
 */
public class TestComparateur {
	private int comparaison;
	private String[] tab;

	public TestComparateur(){

	}
	/**
	 * <p> RechercheComparateur </p> permet de trouver quel(s) comparateur(s) est(sont) utilisé(s)
	 * 
	 * @param condition :condition à parser et a tester
	 * @return tab : tableau de String contenant la colonne ainsi que la table dans laquelle elle se trouve
	 * et la valeur sur laquelle on test
	 */
	public String[] rechercheComparateur(StringBuffer condition){
		Pattern inferieur =Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*<[a-zA-Z0-9.]*");
		Pattern superieur= Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*>[a-zA-Z0-9.]*");
		Pattern infEgal =Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*<=[a-zA-Z0-9.]*");
		Pattern supEgal =Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*>=[a-zA-Z0-9.]*");
		Pattern different =Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*!=[a-zA-Z0-9.]*"); 
		Pattern egal = Pattern.compile("[a-zA-Z0-9]*.[a-zA-Z0-9]*==[a-zA-Z0-9.]*");

		boolean reponseInferieur= inferieur.matcher(condition).matches();
		boolean reponseSuperieur= superieur.matcher(condition).matches();
		boolean reponseInfEgal=infEgal.matcher(condition).matches(); 
		boolean reponseSupEgal=supEgal.matcher(condition).matches(); 
		boolean reponseDifferent=different.matcher(condition).matches();
		boolean reponseEgal= egal.matcher(condition).matches();

		if(reponseInferieur){
			tab=condition.toString().split("[<.]");
			comparaison=1;
		}
		else if(reponseSuperieur){
			tab=condition.toString().split("[>.]");
			comparaison=2;
		}
		else if (reponseInfEgal){
			String[] subtab={null,null,null};
			tab=condition.toString().split("[<=.]");
			for(int i=0;i<tab.length;i++){
				try{
					if(tab[i].isEmpty()){
						subtab[i]=tab[i+1];
					}
					else{
						subtab[i]=tab[i];
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
			}
			tab=subtab;
			comparaison=5;
		}
		else if(reponseSupEgal){
			String[] subtab={null,null,null};
			tab=condition.toString().split("[>=.]");
			for(int i=0;i<tab.length;i++){
				try{
					if(tab[i].isEmpty()){
						subtab[i]=tab[i+1];
					}
					else{
						subtab[i]=tab[i];
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
			}
			tab=subtab;
			comparaison=6;
		}
		else if (reponseDifferent){
			String[] subtab={null,null,null};
			tab=condition.toString().split("[!=.]");
			for(int i=0;i<tab.length;i++){
				try{
					if(tab[i].isEmpty()){
						subtab[i]=tab[i+1];
					}
					else{
						subtab[i]=tab[i];
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
			}
			tab=subtab;
			comparaison=4;
		}
		else if(reponseEgal){
			String[] subtab={null,null,null};
			tab=condition.toString().split("[==.]");
			for(int i=0;i<tab.length;i++){
				try{
					if(tab[i].isEmpty()){
						subtab[i]=tab[i+1];
					}
					else{
						subtab[i]=tab[i];
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
			}
			tab=subtab;
			comparaison=3;
		}
		return tab;

	}
	/**
	 * Renvoie la type de comparaison à effectuer <ul>
	 * <li> < <li> > <li> <= <li> >= <li> == <li> != 
	 * @return comparaison : la valeur attribuée à chaque cas
	 */
	public int getMode(){
		return this.comparaison;
	}
	/**
	 * 
	 * Si la valeur val est <,>,>=,<=,==,!= de valRequete alors la fonction <br/> renvoie vraie sinon elle renvoie faux
	 * 
	 * @param val :valeur de la colonne a comparer 
	 * @param valRequete :valeur entrée dans la requete à laquelle est comparée val
	 * @param mode :type de comparaison demandé
	 * @return bool: si la val est <,>,>=,<=,==,!= à valRequete 
	 */
	public boolean compare(Types val, String valRequete,int mode){
		String p=val.toString();
		boolean bool =false;
		if(val.typeToString().equalsIgnoreCase("int")){			
			switch(mode){
			case 1 :if((Integer.parseInt(p))<(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			case 2: if((Integer.parseInt(p))>(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			case 3:if((Integer.parseInt(p))==(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			case 4:if((Integer.parseInt(p))!=(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			case 5: if((Integer.parseInt(p))<=(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			case 6:if((Integer.parseInt(p))>=(Integer.parseInt(valRequete))){
				bool =true;
			}break;
			}
		}
		else if(val.typeToString().equalsIgnoreCase("char")){
			switch(mode){
			case 3:if(p.equals(valRequete)){
				bool =true;
			}break;
			case 4:if(!p.equals(valRequete)){
				bool =true;
			}break;
			}
		}
		else if(val.typeToString().equalsIgnoreCase("Text")){
			switch(mode){
			case 3:if(p.equals(valRequete)){
				bool =true;
			}break;
			case 4:if(!p.equals(valRequete)){
				bool =true;
			}break;
			}
		}

		else if(val.typeToString().equalsIgnoreCase("float")){
			switch(mode){
			case 1 :if(Float.parseFloat(p)<Float.parseFloat(valRequete)){
				bool =true;
			}break;
			case 2: if(Float.parseFloat(p)>Float.parseFloat(valRequete)){
				bool =true;
			}break;
			case 3:if(Float.parseFloat(p)==Float.parseFloat(valRequete)){
				bool =true;
			}break;
			case 4:if(Float.parseFloat(p)!=Float.parseFloat(valRequete)){
				bool =true;
			}break;
			case 5: if(Float.parseFloat(p)<=Float.parseFloat(valRequete)){
				bool =true;			
			}break;
			case 6:if(Float.parseFloat(p)>=Float.parseFloat(valRequete)){
				bool =true;
			}break;
			}	
		}
		else if(val.typeToString().equalsIgnoreCase("date")){
			Long date1=new Long(val.toString());
			Date ref=new Date(date1);
			Long date2=new Long(valRequete);
			Date var=new Date(date2);

			switch(mode){
			case 1: if(ref.before(var)){
				bool =true;
			}break;
			case 2:if(ref.after(var)){
				bool =true;
			}break;
			case 3:if(ref.equals(var)){
				bool =true;
			}break;
			case 4:if(!ref.equals(var)){
				bool =true;
			}break;
			}
		}
		else if (val.toString().equals("NULL")){
			bool=true;
		}
		return bool;
	}
}

