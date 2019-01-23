package controllers;

import metier.Catalogue;
import metier.I_Catalogue;

public class ctrl_marche 
{
	private static I_Catalogue leCata = Catalogue.getInstance();
	public static boolean Acheter(String nomP,int qte)
	{
		return leCata.acheterStock(nomP, qte);
	}
		
	public static boolean Vendre(String nomP,int qte)
	{
		return leCata.vendreStock(nomP, qte);
	}
}
