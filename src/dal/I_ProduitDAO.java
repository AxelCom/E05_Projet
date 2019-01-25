package dal;

import java.util.List;

import metier.I_Produit;

public interface I_ProduitDAO {

	public abstract void ajouter(I_Produit produit);
	public abstract List<I_Produit> getProduits();
	public abstract I_Produit getProduitByNom(String nomProduit);
	public abstract void modifier(I_Produit produit);
	public abstract void supprimer(I_Produit produit);
}
