package metier;

import java.text.DecimalFormat;
public class Produit implements I_Produit{

	private int qteStock;
	private String nomProduit;
	private double prixUnitaireHT;
	private static double tauxTVA = 0.2F;
	
	public Produit(String leNom, double lePrix, int laQuantite) {
		leNom.replaceAll("\\s"," ");
		leNom = leNom.trim();
		leNom = leNom.replaceFirst("\\s", " ");
		this.nomProduit = leNom;
		this.prixUnitaireHT = lePrix;
		this.qteStock = laQuantite;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {
		this.qteStock += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		this.qteStock -= qteVendue;
		return true;
	}

	@Override
	public String getNom() {
		return this.nomProduit;
	}

	@Override
	public int getQuantite() {
		return this.qteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT+(this.prixUnitaireHT*tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return this.qteStock*getPrixUnitaireTTC();
	}
	
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("#0.00");
		 String prixU = df2.format(prixUnitaireHT);
		 String prixTTC = df2.format(getPrixUnitaireTTC());
		return this.nomProduit + " - prix HT : " + prixU + " € - prix TTC : "+ prixTTC +" € - quantité en stock : "+ this.qteStock + "\n";
	}
}

