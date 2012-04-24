package controller;
import java.util.ArrayList;
import java.util.regex.Pattern;


import model.Column;
import model.Line;
import model.Table;
import types.SChar;
import types.SDate;
import types.Sinteger;
import types.SFloat;
import types.Text;
import types.Types;

public class Instruction {
	private Crud temp=new Crud();

	public Instruction() {

	}

	public Table CreateTable(String requete){
		String[] tab= null;
		String nomTable = new String();
		ArrayList<Types> colTypes=new ArrayList<Types>();
		ArrayList<String> colNames=new ArrayList<String>();

		requete=requete.replace("CREATE TABLE ", "");
		requete=requete.replace("(","");
		requete=requete.replace(" ,"," ");
		requete=requete.replace(", "," ");

		tab=requete.split("[^a-zA-Z0-9]");
		/*for (int i=0;i<tab.length;i++){
			tab[i]=tab[i].trim();
		}*/
		nomTable = tab[0];
		for (int i=1;i<tab.length;i++){
			if(i%2==0){
				try {
					colTypes.add(toType(tab[i]));
				} catch (TypeException e) {
				}
			}
			else{
				colNames.add(tab[i]);
			}
		}
		temp.fullCreate(nomTable,colNames,colTypes);
		return temp.getUsedTable();
	}

	public Table SelectFrom(String requete){
		Crud clu=new Crud();
		String[] tab=null;
		ArrayList<String> colNames=new ArrayList<String>();
		StringBuffer condition =new StringBuffer();
		ArrayList<String> tableName =new ArrayList<String>();
		ArrayList<Types> colType =new ArrayList<Types>();
		ArrayList<Line> lines =new ArrayList<Line>();
		int i=0;
		boolean condi;

		requete=requete.replace("SELECT","");
		tab=requete.split("[^a-zA-Z0-9<>!=.]");

		if(requete.contains("WHERE")){
			for(i=1;!tab[i].equals("FROM");++i){
				colNames.add(tab[i]);
				colType.add(temp.getUsedTable().getCol(tab[i]).getType());
			}
			for(i=i+1;!tab[i].equals("WHERE");++i){
				tableName.add(tab[i]);
			}
			for(i=i+1;i<tab.length;++i){
				condition=condition.append(tab[i]);
			}
			condi=Boolean.valueOf(condition.toString());
			if(condi==true){
				System.out.println("ca marche");
			}
			else{
				System.out.println("ca marche pas");
			}
			clu.fullCreate("resultat", colNames, colType,lines);
		}

		else{
			for(i=1;!tab[i].equals("FROM");++i){
				colNames.add(tab[i]);
				colType.add(temp.getUsedTable().getCol(tab[i]).getType());
			}
			for(i=i+1;i<tab.length;++i){
				tableName.add(tab[i]);
			}
			
			int positionColonne=0;
			if(tableName.get(0).equals(temp.getUsedTable().getTableName())){
				
				for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){
					for(int nbColrequete=0;nbColrequete<colNames.size();nbColrequete++){
						
						if(temp.getUsedTable().getArrCol().get(nbColTable).getLabel().equals(colNames.get(nbColrequete))){
							positionColonne=nbColTable;
							
							for(int positionLigne=0;positionLigne<temp.getUsedTable().size();positionLigne++){
								Line line=new Line();
								line.add(temp.getUsedTable().get(positionLigne).get(positionColonne));
								lines.add(line);
							}
							break;
						}
					}
				}
				clu.fullCreate("resultat", colNames, colType,lines);
			}
			else{
				System.out.println("la Table "+tableName.get(0)+" n'existe pas");
				return null;
			}
		}
		return clu.getUsedTable();
	}

	public Table AlterTable(String requete){
		String[] tab= null;
		Column col = null;
		String tableName;

		requete=requete.replace("ALTER TABLE ","");
		tab=requete.split("[^a-zA-Z0-9]");
		tableName=tab[0];

		for(int i=0;i<tab.length;i++){
			tab[i].trim();
		}

		if(tableName.equals(temp.getUsedTable().getTableName())){
			if(tab[1].equalsIgnoreCase("ADD")){
				try {
					col=new Column(tab[2],toType(tab[3]));
				} catch (TypeException e) {
				}
				temp.getUsedTable().addCol(col);
			}
			else if(tab[1].equalsIgnoreCase("DROP")){
				temp.getUsedTable().supCol(temp.getUsedTable().getCol(tab[2]));
			}
			else if(tab[1].equalsIgnoreCase("CHANGE")){
				try {
					temp.getUsedTable().getCol(tab[2]).setLabel(tab[3]);
					temp.getUsedTable().getCol(tab[2]).setType(toType(tab[4]));
				} catch (TypeException e) {
				}
			}
			else if(tab[1].equalsIgnoreCase("MODIFY")){
				try {
					temp.getUsedTable().getCol(tab[2]).setType(toType(tab[3]));
				} catch (TypeException e) {
				}
			}
			return temp.getUsedTable();
		}
		else {
			System.out.println("la Table "+tableName+" n'existe pas");
			return null;
		}
	}

	public Table InsertInto(String requete){
		String[] tab=null;
		String nomTable;
		ArrayList<String> colName = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		int i=1;

		requete=requete.replace("INSERT INTO ","");
		requete=requete.replace("(","");
		tab=requete.split("[^a-zA-Z0-9.\"\'/]");
		nomTable=tab[0];

		for(int j=0;j<tab.length;j++){
			tab[j].trim();
		}

		int positionColonne=0;
		Line l=new Line();

		if(nomTable.equals(temp.getUsedTable().getTableName())){

			for(i=1; !tab[i].equals("VALUES"); ++i){
				colName.add(tab[i]);
			}
			for(i=i+1; i<tab.length; ++i){
				values.add(tab[i]);
			}
			for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){
				for(int nbCol=0;nbCol<colName.size();nbCol++){
					if(temp.getUsedTable().getArrCol().get(nbColTable).getLabel().equals(colName.get(nbCol))){
						positionColonne=nbColTable;

						while(positionColonne>l.size()){
							l.add(new Text("NULL"));
						}
						try{
							if(temp.getUsedTable().getArrCol().get(nbColTable).getType().typeToString().equals(initLine(values.get(nbCol)).typeToString())){
							l.add(initLine(values.get(nbCol)));
							}
							else{
								System.out.println("le type de la colonne ne correspond pas au type de la donnee a inserer");
							}
						} 
						catch (TypeException e) {
						}
						break;
					}
				}
			}
			/**
			 * il faudrat surement remplacler par la suite la declaration new Text("NULL") par 
			 * une declaration null dans la ligne de la Table ->
			 */
			while(l.size()!=temp.getUsedTable().getArrCol().size()){
				l.add(l.size(), new Text("NULL"));
			}
			temp.getUsedTable().insert(l);
		}
		else{
			System.out.println("La Table "+nomTable+" n'existe pas");
			return null;
		}
		
		return temp.getUsedTable();
	}

	public Table Update(String requete){
		String[] tab=null;
		int i = 0;
		Types value=null;
		ArrayList<String> colNames=new ArrayList<String>();
		//ArrayList<Boolean> condition=new ArrayList<Boolean>();
		//boolean condi=Boolean.valueOf("i!=0");

		requete=requete.replace("UPDATE ","");
		tab=requete.split("[^a-zA-Z0-9.\\=\"\']");
		String tableName = tab[0];

		if(requete.contains("WHERE")){/*
			for(i=1;!tab[i].equals("SET");++i){
				colNames.add(tab[i]);
			}
			for(i=i+1;!tab[i].equals("WHERE");++i){
				tableNames.add(tab[i]);
			}
			for(i=i+1;i<tab.length;++i){
				//condition=condition.append(tab[i]);
			}
			condi=Boolean.valueOf(condition.toString());
			if(condi==true){
				System.out.println("ca marche");
			}
			else{ System.out.println("ca marche pas");}

		 */}
		else{
			for(i=1;!tab[i].equals("SET");++i){
			}
			if(tableName.equals(temp.getUsedTable().getTableName())){
				for(i=i+1;!tab[i].equals("=");++i){
					colNames.add(tab[i]);
				}
				for(i=i+1;i<tab.length;++i){
					try {
						value=initLine(tab[i]);
					} catch (TypeException e) {
					}

				}

				int positionColonne=0;
				for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){
					for(int nbCol=0;nbCol<colNames.size();nbCol++){
						if(temp.getUsedTable().getArrCol().get(nbColTable).getLabel().equals(colNames.get(nbCol))){
							positionColonne=nbColTable;
							for(int positionLigne=0;positionLigne<temp.getUsedTable().size();positionLigne++){
								temp.getUsedTable().get(positionLigne).set(positionColonne,value);
							}
							break;
						}
					}
				}
				//return temp.getUsedTable();
			}
			else{
				System.out.println("La Table "+tableName+" n'existe pas ");
				return null;
			}
		}
		return temp.getUsedTable();
	}
	
	public Table DeleteFrom(String requete){
		requete=requete.replace("DELETE FROM ", "");
		String[] tab = requete.split("^[a-zA-Z]");
		temp=new Crud();
		temp.fullCreate(tab[0],null,null);
		return temp.getUsedTable();
	}

	public Types toType(String requete)throws TypeException{
		Types type = null;
		if(requete.equalsIgnoreCase("int")){
			type=new Sinteger();
		}
		else if(requete.equalsIgnoreCase("float")){
			type= new SFloat();
		}
		else if(requete.equalsIgnoreCase("date")){
			type= new SDate();
		}
		else if(requete.equalsIgnoreCase("char")){
			type= new SChar();
		}
		else if(requete.equalsIgnoreCase("text")){
			type= new Text();
		}
		else {
			throw new TypeException(requete);
		}
		return type;
	}
	public Types initLine(String val) throws TypeException{
		Types type = null;

		Pattern Char=Pattern.compile("^[A-Za-z]$");
		Pattern Date=Pattern.compile("^[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}$");
		Pattern Float=Pattern.compile("^[0-9]*\\.[0-9]*$");
		Pattern Sinteger=Pattern.compile("^[0-9]*$");
		Pattern Text=Pattern.compile("^\"[a-zA-Z]*\"$");


		boolean reponseChar =Char.matcher(val).matches();
		boolean reponseDate =Date.matcher(val).matches();
		boolean reponseFloat =Float.matcher(val).matches();
		boolean reponseSinteger =Sinteger.matcher(val).matches();
		boolean reponseText= Text.matcher(val).matches();

		if(reponseSinteger){
			type=new Sinteger(Integer.parseInt(val));
		}
		else if(reponseFloat){
			type= new SFloat(java.lang.Float.parseFloat(val));
		}
		else if(reponseDate){
			String[] tab=val.split("/");
			type= new SDate(Integer.parseInt(tab[0]),Integer.parseInt(tab[1]),Integer.parseInt(tab[2]));
		}
		else if(reponseChar){
			int length=val.toCharArray().length;
			type= new SChar(val.toCharArray(),length);
		}
		else if(reponseText){
			type= new Text(val);
		}
		else{
			throw new TypeException(val);
		}
		return type;
	}
}
