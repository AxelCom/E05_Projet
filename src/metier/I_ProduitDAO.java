package metier;

public interface I_ProduitDAO {

	public abstract void ajouter(String nomProduit, double prixUnitaireHT, int qteStock);
	public abstract void getProduits();
	public abstract void modifier(String nomProduit, int qteStock);
	public abstract void supprimer(String nomProduit);
	public abstract void deconnexion();
}
