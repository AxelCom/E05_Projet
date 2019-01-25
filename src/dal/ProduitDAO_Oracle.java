package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;


public class ProduitDAO_Oracle implements I_ProduitDAO {
	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProduitDAO_Oracle() {
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
	}

	@Override
	public void ajouter(I_Produit produit) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nomProduit", produit.getNom());
			rs.updateDouble("prixUnitaireHT", produit.getPrixUnitaireHT());
			rs.updateInt("qteStock", produit.getQuantite());
			rs.insertRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<I_Produit> getProduits() {
		ArrayList<I_Produit> listeProduits = new ArrayList<I_Produit>();
		try {
			while (rs.next()) {
				listeProduits.add(gererProduit(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	@Override
	public I_Produit getProduitByNom(String nomProduit) {
		try {
			
			pst.setString(1, nomProduit);
			rs = pst.executeQuery();
			if(rs.next()) {
				gererProduit(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifier(I_Produit produit) {
		try {
			pst.setString(1, produit.getNom());
			rs = pst.executeQuery();
			if(rs.next()) {
				rs.updateDouble("qteStock", produit.getQuantite());
				rs.updateRow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void supprimer(I_Produit produit) {
		try {
			pst.setString(1, produit.getNom());
			rs = pst.executeQuery();
			if(rs.next()) {
				rs.deleteRow();
			}
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
			unProduit = new Produit(nomProduit,prixHT,qteStock);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return unProduit;
	}
	
	private void deconnexion() {
		try {
			cn.close();
			System.out.println("Connexion Fermée !");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
}
