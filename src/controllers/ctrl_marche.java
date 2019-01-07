package controllers;

import metier.I_Produit;

public class ctrl_marche 
{
	public static boolean Acheter(I_Produit unProduit,int qte)
	{
		return unProduit.ajouter(qte);
	}
		
	public static boolean Vendre(I_Produit unProduit,int qte)
	{
		return unProduit.enlever(qte);
	}
}
