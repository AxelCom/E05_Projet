package controllers;
import java.util.ArrayList;

import metier.I_Produit;
import metier.Produit;
public class ctrl_produit {

	public static Produit createProduit(String leNom, double lePrix, int laQuantite)
	{
		Produit unProduit = new Produit(leNom, lePrix, laQuantite);
		return unProduit;
	}
	
	//TODO methode supprimerProduit qui supprime un produit des catalogue et de la bdd
}
