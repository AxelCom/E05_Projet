package metier;
import java.sql.*;
import java.util.ArrayList;


public class ConnexionOracle implements I_ProduitDAO {
	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ConnexionOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
			String login = "commergnata";
			String mdp = "1109015674V";
			cn = DriverManager.getConnection(url, login, mdp);
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst = cn.prepareStatement("SELECT nomProduit, prixUnitaireHT, qteStock from Produits where nomProduit = ? order by nomProduit asc",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT nomProduit, prixUnitaireHT, qteStock from Produits order by nomProduit asc");
		}
		catch(ClassNotFoundException e){
			System.out.println("Problème de driver !");
		}
		catch(SQLException e) {
			System.out.println("Problème SQL : " + e);
			try{
				cn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		getProduits();
	}
	
	@Override
	public void deconnexion() {
		try {
			cn.close();
			System.out.println("Connexion Fermée !");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ajouter(String nomProduit, double prixUnitaireHT, int qteStock) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nomProduit", nomProduit);
			rs.updateDouble("prixUnitaireHT", prixUnitaireHT);
			rs.updateInt("qteStock", qteStock);
			rs.insertRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void getProduits() {
		ArrayList<I_Produit> listeProduits = new ArrayList<I_Produit>();
		try {
			while (rs.next()) {
				listeProduits.add(gererProduit(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Catalogue.getInstance().addProduits(listeProduits);
	}

	@Override
	public void modifier(String nomProduit, int qteStock) {
		try {
			pst.setString(1, nomProduit);
			rs = pst.executeQuery();
			if(rs.next()) {
				rs.updateDouble("qteStock", qteStock);
				rs.updateRow();
			}
			chargerProduits();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void supprimer(String nomProduit) {
		try {
			pst.setString(1, nomProduit);
			rs = pst.executeQuery();
			if(rs.next()) {
				rs.deleteRow();
			}
			chargerProduits();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private I_Produit gererProduit(ResultSet rs) {
		String nomProduit;
		double prixHT;
		int qteStock;
		I_Produit unProduit = null;
		try {
			nomProduit = rs.getString("nomProduit");
			prixHT = rs.getDouble("prixUnitaireHT");
			qteStock = rs.getInt("qteStock");
			System.out.println(nomProduit + " " + prixHT + " " + qteStock);
			unProduit = new Produit(nomProduit,prixHT,qteStock);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return unProduit;
	}
	
	private void chargerProduits() {
		try {
			rs = st.executeQuery("SELECT nomProduit, prixUnitaireHT, qteStock from Produits order by nomProduit asc");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
