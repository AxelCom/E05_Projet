package controllers;

import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;

public class ctrl_Catalogue 
{
	
	public static Catalogue createCatalogue()
	{
		Catalogue unCata = new Catalogue();
		return unCata;
	}
	
	public static void addProduit(I_Catalogue leCat,String nomPrd, double lePrix, int laQuantite)
	{
		I_Produit leProduit =  ctrl_produit.createProduit(nomPrd, lePrix, laQuantite);
		leCat.addProduit(leProduit);
	}
}
