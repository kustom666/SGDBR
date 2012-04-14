package controller;

public class TestPatternException extends Exception{
	private static final long serialVersionUID = 1L;

	public TestPatternException(){
		System.out.println("requete inconnue ou mauvaise utilisation de la requete \n");
	}
}