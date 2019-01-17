package controllers;

import metier.Catalogue;
import metier.I_Catalogue;
public class ctrl_produit {

	private static I_Catalogue monCata = Catalogue.getInstance();
	
	public static void createProduit(String leNom, double lePrix, int laQuantite)
	{
		// rajouter a la bdd
		monCata.addProduit(leNom, lePrix, laQuantite);
	}
	
	public static void deleteProduit(String leNom) {
		monCata.removeProduit(leNom);
	}
	public static String[] afficherNomProduits()
	{
		return monCata.getNomProduits();
	}
}
