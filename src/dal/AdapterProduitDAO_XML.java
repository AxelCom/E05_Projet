package dal;

import java.util.List;

import metier.I_Produit;

public class AdapterProduitDAO_XML implements I_ProduitDAO{

	private ProduitDAO_XML produitXML;
	
	public AdapterProduitDAO_XML() {
		produitXML = new ProduitDAO_XML();
	}
	@Override
	public void ajouter(I_Produit produit) {
		produitXML.creer(produit);
	}

	@Override
	public List<I_Produit> getProduits() {
		return produitXML.lireTous();
	}

	@Override
	public I_Produit getProduitByNom(String nomProduit) {
		return produitXML.lire(nomProduit);
	}

	@Override
	public void modifier(I_Produit produit) {
		produitXML.maj(produit);
	}

	@Override
	public void supprimer(I_Produit produit) {
		produitXML.supprimer(produit);
	}
}
