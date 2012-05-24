package main;

import java.util.ArrayList;
import java.util.Scanner;

<<<<<<< HEAD
import types.Types;
import types.Integer;
=======
import types.Sinteger;
import types.Types;
import types.Sinteger;
>>>>>>> e4393b78591058f67e6c265aacd7ca5a821e72cd
import model.Table;

public class Main {

	public static void main(String[] args) {
		ArrayList<Types> al = new ArrayList<Types>();
<<<<<<< HEAD
		al.add(new Integer());
		Table buffTable = new Table(args, al);

=======
		al.add(new Sinteger(18));
		ArrayList<String> alS = new ArrayList<String>();
		alS.add("numero");
		Table buffTable = new Table(alS, al);
		
		
		
>>>>>>> e4393b78591058f67e6c265aacd7ca5a821e72cd
	}

}
