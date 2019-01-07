package controllers;

import java.util.ArrayList;

import metier.I_Produit;

public class ctrl_stock 
{
	
	 public static  String AfficherStock(ArrayList<I_Produit> leCatalogue)
	 {
		 String affStock = "";
		 for (I_Produit leProduit : leCatalogue) {
			affStock +=leProduit.toString()+"\n";
		 }
		 affStock += leCatalogue.toString();
		 return affStock;
	 }
	 
	public static boolean ajouterStock(I_Produit unProduit,int qte)
	{
		return unProduit.ajouter(qte);
	}
		
	public static boolean enleverStock(I_Produit unProduit,int qte)
	{
		return unProduit.enlever(qte);
	}
}
