package dal;

public class ProduitDAOFactory {
	
	private static ProduitDAOFactory instance = null;
	
	private ProduitDAOFactory() {
	}
	
	public static ProduitDAOFactory getInstance() {
		if(instance == null) {
			instance = new ProduitDAOFactory();
		}
		return instance;
	}
	
	public I_ProduitDAO createConnexionI_PrdoduitDAO(String typeConnexion) {
		if(typeConnexion == "Oracle") {
			return new ProduitDAO_Oracle();
		}
		else {
			return new AdapterProduitDAO_XML();
		}
	}

}
