package controller;
import java.util.ArrayList;
import java.util.regex.Pattern;


import model.Base;
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
	private baseController starCommand=new baseController();


	public Instruction() {

	}

	public Base CreateTable(String requete){
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
		starCommand.ajouterTable(temp.getUsedTable());

		return starCommand.getBaseTravail();
	}

	public Base SelectFrom(String requete){
		Crud clu=new Crud();
		baseController base=new baseController();
		String[] tab=null;
		String[] subtab=null;
		ArrayList<String> colNames=new ArrayList<String>();
		StringBuffer condition =new StringBuffer();
		ArrayList<String> tableName =new ArrayList<String>();
		ArrayList<Types> colType =new ArrayList<Types>();
		ArrayList<Line> lines =new ArrayList<Line>();
		int i=0;

		requete=requete.replace("SELECT","");
		tab=requete.split("[^a-zA-Z0-9<>!=.]");

		if(requete.contains("WHERE")){

			for(i=1;!tab[i].equals("FROM");++i){
				colNames.add(tab[i]);
			}
			for(i=i+1;!tab[i].equals("WHERE");++i){
				tableName.add(tab[i]);
			}
			for(i=i+1;i<tab.length;++i){
				condition=condition.append(tab[i]);
			}
			subtab=condition.toString().split("<=");
			for(int j=0;j<tableName.size();j++){
				for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
					if(tableName.get(j).equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
						temp.setUsedTable(starCommand.getBaseTravail().getTable(tableName.get(j)));
						for(int iter=0;iter<colNames.size();iter++){
							for(int n=0;n<temp.getUsedTable().getArrCol().size();n++){
								if(colNames.get(iter).equals(temp.getUsedTable().getArrCol().get(n).getLabel())){
									colType.add(temp.getUsedTable().getCol(colNames.get(iter)).getType());
									break;
								}
							}
						}
					}
				}
			}
			int nbLigne=0;
			clu.fullCreate("resultat", colNames, colType);

			for(int j=0;j<tableName.size();j++){
				if(j>0 && starCommand.getBaseTravail().getTable(tableName.get(j)).size()>starCommand.getBaseTravail().getTable(tableName.get(j-1)).size()){
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(j)).size();
				}
				else if(j>0){
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(j-1)).size();
				}
				else{
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(0)).size();
				}
			}
			if(subtab.length==2){
				int positionColonne=0;
				for(int nbColTab=0;nbColTab<temp.getUsedTable().getArrCol().size();nbColTab++){
					if(subtab[0].equals(temp.getUsedTable().getArrCol().get(nbColTab).getLabel())){
						positionColonne = nbColTab;
						break;
					}
				}
				for(int lLigne=0;lLigne<temp.getUsedTable().size();lLigne++){
					if(Integer.parseInt(temp.getUsedTable().get(lLigne).get(positionColonne).toString())<=Integer.parseInt(subtab[1])){
						for(int ligne=0;ligne<nbLigne;ligne++){
							Line line=new Line();
							for(int j=0;j<tableName.size();j++){
								for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
									if(tableName.get(j).equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
										temp.setUsedTable(starCommand.getBaseTravail().getTable(tableName.get(j)));

										for(int nbColrequete=0;nbColrequete<colNames.size();nbColrequete++){
											for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){

												if(colNames.get(nbColrequete).equals(temp.getUsedTable().getArrCol().get(nbColTable).getLabel())){
													try{
														line.add(temp.getUsedTable().get(ligne).get(nbColTable));
													}
													catch(IndexOutOfBoundsException e){
														line.add(new Text("NULL"));
													}
													lines.add(line);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				clu.ajouterLignes(lines);
				base.ajouterTable(clu.getUsedTable());
			}
		}

		else{

			for(i=1;!tab[i].equals("FROM");++i){
				colNames.add(tab[i]);

			}
			for(i=i+1;i<tab.length;++i){
				tableName.add(tab[i]);
			}
			for(int j=0;j<tableName.size();j++){
				for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
					if(tableName.get(j).equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
						temp.setUsedTable(starCommand.getBaseTravail().getTable(tableName.get(j)));
						for(int iter=0;iter<colNames.size();iter++){
							for(int n=0;n<temp.getUsedTable().getArrCol().size();n++){
								if(colNames.get(iter).equals(temp.getUsedTable().getArrCol().get(n).getLabel())){
									colType.add(temp.getUsedTable().getCol(colNames.get(iter)).getType());
									break;
								}
							}
						}
					}
				}
			}
			int nbLigne=0;
			clu.fullCreate("resultat", colNames, colType,lines);

			for(int j=0;j<tableName.size();j++){
				if(j>0 && starCommand.getBaseTravail().getTable(tableName.get(j)).size()>starCommand.getBaseTravail().getTable(tableName.get(j-1)).size()){
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(j)).size();
				}
				else if(j>0){
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(j-1)).size();
				}
				else{
					nbLigne=starCommand.getBaseTravail().getTable(tableName.get(0)).size();
				}
			}
			for(int ligne=0;ligne<nbLigne;ligne++){
				Line line=new Line();
				for(int j=0;j<tableName.size();j++){
					for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
						if(tableName.get(j).equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
							temp.setUsedTable(starCommand.getBaseTravail().getTable(tableName.get(j)));

							for(int nbColrequete=0;nbColrequete<colNames.size();nbColrequete++){
								for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){

									if(colNames.get(nbColrequete).equals(temp.getUsedTable().getArrCol().get(nbColTable).getLabel())){
										try{
											line.add(temp.getUsedTable().get(ligne).get(nbColTable));
										}
										catch(IndexOutOfBoundsException e){
											line.add(new Text("NULL"));
										}
									}
								}
							}
						}
					}
				}
				lines.add(line);
			}
			clu.ajouterLignes(lines);
			base.ajouterTable(clu.getUsedTable());
		}
		return base.getBaseTravail();
	}


	public Base AlterTable(String requete){
		String[] tab= null;
		Column col = null;
		String tableName=new String();
		baseController base= new baseController();
		int positionColonne = 0;

		requete=requete.replace("ALTER TABLE ","");
		tab=requete.split("[^a-zA-Z0-9]");
		tableName=tab[0];

		for(int i=0;i<tab.length;i++){
			tab[i].trim();
		}
		for(int i=0;i<starCommand.getBaseTravail().size();i++){
			if(tableName.equals(starCommand.getBaseTravail().get(i).getTableName())){
				temp.setUsedTable(starCommand.getBaseTravail().get(i));

				if(tab[1].equals("ADD")){
					try {
						col=new Column(tab[2],toType(tab[3]));
					} catch (TypeException e) {
					}
					temp.getUsedTable().addCol(col);
				}
				else if(tab[1].equalsIgnoreCase("DROP")){

					for(int j=0;j<temp.getUsedTable().getArrCol().size();j++){
						if(temp.getUsedTable().getArrCol().get(j).getLabel().equals(tab[2])){
							positionColonne=j;
							break;
						}
					}
					for(int j=0;j<temp.getUsedTable().size();j++){
						for(int p=0;p<temp.getUsedTable().get(j).size();p++){
							if(p==positionColonne){
								temp.getUsedTable().get(j).remove(p);
							}
						}
					}
					temp.getUsedTable().supCol(temp.getUsedTable().getCol(tab[2]));
				}
				else if(tab[1].equalsIgnoreCase("CHANGE")){
					try {
						(temp.getUsedTable().getCol(tab[2])).setType(toType(tab[4]));
						(temp.getUsedTable().getCol(tab[2])).setLabel(tab[3]);
					} catch (TypeException e) {
					}
				}
				else if(tab[1].equalsIgnoreCase("MODIFY")){
					try {
						(temp.getUsedTable().getCol(tab[2])).setType(toType(tab[3]));
					} catch (TypeException e) {
					}
				}
			}
		}
		base.ajouterTable(temp.getUsedTable());
		return base.getBaseTravail();
	}

	public Base InsertInto(String requete){
		String[] tab=null;
		ArrayList<String> tableName = new ArrayList<String>();
		ArrayList<String> colName = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		int i=0;

		requete=requete.replace("INSERT INTO ","");
		requete=requete.replace(")","");
		tab=requete.split(" ");

		int positionColonne=0;
		Line l=new Line();

		for(i=0;!tab[i].startsWith("(");++i){
			tableName.add(tab[i]);
		}

		for(int j=0;j<tab.length;j++){
			requete.concat(tab[i]);
		}
		requete=requete.replace("(", "");
		tab=requete.split("[^a-zA-Z0-9.\"\'/]");



		for(int j=0;j<tableName.size();j++){
			for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
				if(tableName.get(j).equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
					temp.setUsedTable(starCommand.getBaseTravail().getTable(tableName.get(j)));

					for(i=i;!tab[i].equals("VALUES"); ++i){
						colName.add(tab[i]);
					}
					for(i=i+1; i<tab.length; ++i){
						values.add(tab[i]);
					}
					for(int nbCol=0;nbCol<colName.size();nbCol++){
						for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){	
							if(colName.get(nbCol).equals(temp.getUsedTable().getArrCol().get(nbColTable).getLabel())){
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
					while(l.size()<temp.getUsedTable().getArrCol().size()){
						l.add(l.size(), new Text("NULL"));

					}
					temp.getUsedTable().insert(l);
				}
			}
		}
		return starCommand.getBaseTravail();
	}

	public Base Update(String requete){
		String[] tab=null;
		int i = 0;
		int positionTable=0;
		Types value=null;
		String colNames=new String();
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
			for(int nbTable=0;nbTable<starCommand.getBaseTravail().size();nbTable++){
			if(tableName.equals(starCommand.getBaseTravail().get(nbTable).getTableName())){
				temp.setUsedTable(starCommand.getBaseTravail().get(nbTable));
				positionTable=nbTable;
				
				for(i=2;!tab[i].equals("=");++i){
					colNames=(tab[i]);
				}
				for(i=i+1;i<tab.length;++i){
					try {
						value=initLine(tab[i]);
					} catch (TypeException e) {
					}

				}
			
				int positionColonne=0;
				for(int nbColTable=0;nbColTable<temp.getUsedTable().getArrCol().size();nbColTable++){
						if(temp.getUsedTable().getArrCol().get(nbColTable).getLabel().equals(colNames)){
							positionColonne=nbColTable;
							
							for(int positionLigne=0;positionLigne<temp.getUsedTable().size();positionLigne++){
								temp.getUsedTable().get(positionLigne).set(positionColonne,value);
							}
							break;
						}
					}
				starCommand.getBaseTravail().set(positionTable, temp.getUsedTable());
				break;
				}
			}
		}
		return starCommand.getBaseTravail();
	}

	public Base DeleteFrom(String requete){
		requete=requete.replace("DELETE FROM ", "");
		String[] tab = requete.split("[^a-zA-Z]");
		
		if(requete.contains("WHERE")){
			
		}
		else{
			String tableName=tab[0];
			for(int i=0;i<starCommand.getBaseTravail().size();i++){
				if(tableName.equals(starCommand.getBaseTravail().get(i).getTableName())){
					starCommand.getBaseTravail().remove(i);
					break;
				}
			}
		}
		return starCommand.getBaseTravail();
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
