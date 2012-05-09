package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Crud;
import controller.Instruction;
import controller.TestPattern;
import controller.TestPatternException;
import controller.baseController;

import types.SFloat;
import types.Sinteger;
import types.Text;
import types.Types;
import types.Sinteger;
import model.Base;
import model.Line;
import model.Table;

public class Main {

	public static void main(String[] args) {
	/*	Line lb = new Line();
		Line l = new Line();
		l.add(new SFloat(19f));
		l.add(new Sinteger(100));
		l.add(new Text("182"));
		lb.add(new SFloat(21f));
		lb.add(new Sinteger(100));
		lb.add(new Text("170"));

		ArrayList<String> alS = new ArrayList<String>();
		ArrayList<Types> alT = new ArrayList<Types>();
		ArrayList<Line> al = new ArrayList<Line>();

		al.add(l);
		al.add(lb);
		
		alS.add("Age");
		alS.add("Age max");
		alS.add("Taille");

		alT.add(new SFloat());
		alT.add(new Sinteger());
		alT.add(new Text());
*/

		Crud controlleur = new Crud();
		Table table = null;
		TestPattern t=new TestPattern();
//		controlleur.setUsedTable(table);
		//controlleur.fullCreate("Table dummy", alS, alT, al);
		//System.out.println(controlleur);
		String test = "y";
		System.out.println("Bienvenue sur le CLI SGDBR 0.1a, initialisation de la base");
		Base base=new Base("default");
		System.out.println("Base default initialisée, en attente de commandes");
		while(!test.equals("n"))
		{	
			String buffIn = new String();
			while(!buffIn.endsWith(";"))
			{
				Scanner sc = new Scanner(System.in);
				buffIn += sc.nextLine();
			}

			try {
				base=(t.test(buffIn));
				
			} catch (TestPatternException e) {
				e.printStackTrace();
			}
			for(int i=0;i<base.size();i++){
			controlleur.setUsedTable(base.get(i));
			controlleur.displayTable();
			if(controlleur.getUsedTable().getTableName().equals("resultat")){
				base.remove(controlleur.getUsedTable().getTableName());
			}
			}
			
			System.out.println("y-Continuer, n-Arrèter");
			Scanner sc = new Scanner(System.in);
			test += sc.nextLine().trim();
		}

	}
}
