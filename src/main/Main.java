package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Crud;

import types.Sinteger;
import types.Text;
import types.Types;
import types.Sinteger;
import model.Base;
import model.Line;
import model.Table;

public class Main {

	public static void main(String[] args) {
		
		Line l = new Line();
		Line lb = new Line();
		Line lc = new Line();
		l.add(new Text("Paul"));
		l.add(new Sinteger(19));
		l.add(new Sinteger(100));
		l.add(new Sinteger(182));
		
		lb.add(new Text("Tony"));
		lb.add(new Sinteger(21));
		lb.add(new Sinteger(100));
		lb.add(new Sinteger(170));
		
		lc.add(new Text("Ludo"));
		lc.add(new Sinteger(20));
		lc.add(new Sinteger(100));
		lc.add(new Sinteger(180));
		
		
		ArrayList<String> alS = new ArrayList<String>();
		ArrayList<Types> alT = new ArrayList<Types>();
		ArrayList<Line> al = new ArrayList<Line>();
		
		al.add(l);
		al.add(lb);
		al.add(lc);
		
		alT.add(new Text());
		alT.add(new Sinteger());
		alT.add(new Sinteger());
		alT.add(new Sinteger());
		
		alS.add("Nom");
		alS.add("Age");
		alS.add("Age max");
		alS.add("Taille");

		
		Crud controlleur = new Crud();
		controlleur.fullCreate("Table dummy", alS, alT, al);
		controlleur.displayTable();
		
		/*
		System.out.println("Bienvenue sur le CLI SGDBR 0.1a, initialisation de la base");
		Base workingBase = new Base("default");
		System.out.println("Base default initialisée, en attente de commandes");
		String buffIn = new String();
		
		while(!buffIn.endsWith(";"))
		{
			Scanner sc = new Scanner(System.in);
			buffIn += sc.nextLine();
		}//*/
		
	}

}
