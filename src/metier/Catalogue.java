package metier;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import metier.*;
public class Catalogue implements I_Catalogue{
	
	private ArrayList<I_Produit> lesProduits = new ArrayList<I_Produit>();
	private static Catalogue instance = null;
	
	private Catalogue() {
		
	}
	
	public static Catalogue getInstance()
	{
		if(instance == null)
		instance = new Catalogue();
		return instance;
	}
	@Override //DONE
	public boolean addProduit(I_Produit produit) {
		try {
			if(!nameAlreadyUse(produit.getNom()) && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0 ) {
			lesProduits.add(produit);
			return true;
			}
			return false;
		}
		catch(NullPointerException e) {
			return false;
		}
	}

	@Override //DONE
	public boolean addProduit(String nom, double prix, int qte) {
		try {
			if(!nameAlreadyUse(nom)  && prix > 0 && qte >= 0) {
				Produit leProduit = new Produit(nom,prix,qte);
				lesProduits.add(leProduit);
				return true;
			}
			return false;
		}
		catch(NullPointerException e){
			return false;
		}

	}

	@Override //DONE???
	public int addProduits(List<I_Produit> l) {
		try {
			int nbInsert = 0;
		for (I_Produit leProduit : l) {
			if(leProduit.getPrixUnitaireHT() > 0 && leProduit.getQuantite() >= 0 && !nameAlreadyUse(leProduit.getNom())) {
				lesProduits.add(leProduit);
				nbInsert++;
			}
		}
		return nbInsert;
		}
		catch(NullPointerException e){
			return 0;
		}
	}

	@Override // DONE ??? check flo
	public boolean removeProduit(String nom) {
		int i = 0;
		
		while(i<(lesProduits.size()-1) && !lesProduits.get(i).getNom().equals(nom))
		{
			i++;
		}
		if(lesProduits.get(i).getNom().equals(nom))
		{
			lesProduits.remove(lesProduits.get(i));
			return true;
		}
		return false;
	}

	@Override // DONE ?? check flo
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		int i = 0;
		if(qteAchetee > 0) {
			while(i<(lesProduits.size()-1) && !lesProduits.get(i).getNom().equals(nomProduit))
			{
				i++;
			}
			if(lesProduits.get(i).getNom().equals(nomProduit))
			{
				lesProduits.get(i).ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}

	@Override // DONE ?? check flo
	public boolean vendreStock(String nomProduit, int qteVendue) {
			int i = 0;
			if(qteVendue > 0) {
				while(i<(lesProduits.size()-1) && !lesProduits.get(i).getNom().equals(nomProduit))
				{
					i++;
				}
				if(lesProduits.get(i).getNom().equals(nomProduit))
				{
					if(lesProduits.get(i).getQuantite() >= qteVendue ) {
						lesProduits.get(i).enlever(qteVendue);
						return true;
					}
				}
				return false;
			}
			return false;
	}

	@Override
	public String[] getNomProduits() {
		int count = 0;
		String [] lesNoms = new String[lesProduits.size()];
		for (I_Produit leProduit : this.lesProduits) {
			lesNoms[count] = leProduit.getNom();
			count++;
		}
		Arrays.sort(lesNoms);
		return lesNoms;
	}

	@Override //DONE ???
	public double getMontantTotalTTC() {
		double result = 0;
		for (I_Produit leProduit : lesProduits) {
			result += leProduit.getPrixStockTTC();
		}
		//DecimalFormat df2 = new DecimalFormat("#0.00");
		//String test = df2.format(result);
		//double result1 = Double.parseDouble(test);
		//System.out.println(test);
		return Math.round(result*100.0)/100.0;
	}

	@Override //DONE
	public void clear() {
		this.lesProduits.clear();
	}
	private boolean nameAlreadyUse(String nom) {
		for (String unNom : getNomProduits()) {
			nom = nom.trim();
			if(nom.equals(unNom)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		if(lesProduits.size() > 0) {
			//TODO
			String phrases = "";
			for (I_Produit unProd : lesProduits) {
				phrases += unProd;
			}
			DecimalFormat df2 = new DecimalFormat("#0.00");
			String montantTTTC = df2.format(this.getMontantTotalTTC());
			return  phrases +"\n" +"Montant total TTC du stock : "+ montantTTTC +" €";
		}
		else {					 
			return "\n" +"Montant total TTC du stock : 0,00 €";
		}
	}
}

