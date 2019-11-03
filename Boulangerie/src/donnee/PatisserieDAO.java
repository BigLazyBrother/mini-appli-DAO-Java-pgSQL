package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Patisserie;

public class PatisserieDAO implements PatisserieSQL {
		
	private Connection connection = null;
	
	public PatisserieDAO()
	{
		this.connection = BaseDeDonnees.getInstance().getConnection();		
	}
	
	@SuppressWarnings("unused")
	private List<Patisserie> simulerListerPatisseries()	{
		List<Patisserie> listePatisserieTest = new ArrayList<Patisserie>();
		listePatisserieTest.add(new Patisserie("Donut", "brun", "200", "2"));
		listePatisserieTest.add(new Patisserie("Croissant", "blanc", "120", "3"));
		listePatisserieTest.add(new Patisserie("Mille-feuilles", "bariollé", "350", "5"));
		listePatisserieTest.add(new Patisserie("Cheese-cake", "Fuschia", "350", "5"));
		return listePatisserieTest;
	}	
	
	public List<Patisserie> listerPatisseries()	{

		List<Patisserie> listePatisseries =  new ArrayList<Patisserie>();
		Statement requeteListePatisseries;
		try {
			requeteListePatisseries = connection.createStatement();
			ResultSet curseurListePatisseries = requeteListePatisseries.executeQuery(PatisserieSQL.SQL_LISTER_PATISSERIES);
			while(curseurListePatisseries.next()) {
				int id = curseurListePatisseries.getInt("id");
				String nom = curseurListePatisseries.getString("nom");
				String couleur = curseurListePatisseries.getString("couleur");
				String poids = curseurListePatisseries.getString("poids");
				String prix = curseurListePatisseries.getString("prix");
				System.out.println("Patisserie « " + nom + " » coûte $" + prix + ", pèse " + poids + "g et a un glaçage " + couleur + ".");
				Patisserie patisserie = new Patisserie(nom, couleur, poids, prix);
				patisserie.setId(id);
				listePatisseries.add(patisserie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		//return this.simulerListerPatisseries();
		return listePatisseries;
	}
	
	public void ajouterPatisserie(Patisserie patisserie) {
		System.out.println("PatisserieDAO.ajouterPatisserie()");
		try {
			PreparedStatement requeteAjouterPatisserie = connection.prepareStatement(SQL_AJOUTER_PATISSERIE);
			requeteAjouterPatisserie.setString(1, patisserie.getNom());
			requeteAjouterPatisserie.setString(2, patisserie.getCouleur());
			requeteAjouterPatisserie.setString(3, patisserie.getPoids());
			requeteAjouterPatisserie.setString(4, patisserie.getPrix());
			
			System.out.println("SQL : " + SQL_AJOUTER_PATISSERIE);
			requeteAjouterPatisserie.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierPatisserie(Patisserie patisserie) {
		System.out.println("PatisserieDAO.modifierPatisserie()");
		try {
			PreparedStatement requeteModifierPatisserie = connection.prepareStatement(SQL_MODIFIER_PATISSERIE);
			requeteModifierPatisserie.setString(1, patisserie.getNom());
			requeteModifierPatisserie.setString(2, patisserie.getCouleur());
			requeteModifierPatisserie.setString(3, patisserie.getPoids());
			requeteModifierPatisserie.setString(4, patisserie.getPrix());
			requeteModifierPatisserie.setInt(5, patisserie.getId());
			
			System.out.println("SQL : " + SQL_MODIFIER_PATISSERIE);
			requeteModifierPatisserie.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Patisserie rapporterPatisserie(int idPatisserie)	{
		PreparedStatement requetePatisserie;
		try {
			requetePatisserie = connection.prepareStatement(SQL_RAPPORTER_PATISSERIE);
			requetePatisserie.setInt(1, idPatisserie);
			System.out.println(SQL_RAPPORTER_PATISSERIE);
			ResultSet curseurPatisserie = requetePatisserie.executeQuery();
			curseurPatisserie.next();
			int id = curseurPatisserie.getInt("id");
			String nom = curseurPatisserie.getString("nom");
			String couleur = curseurPatisserie.getString("couleur");
			String poids = curseurPatisserie.getString("poids");
			String prix = curseurPatisserie.getString("prix");
			System.out.println("Patisserie « " + nom + " » coûte $" + prix + ", pèse " + poids + "g et a un glaçage " + couleur + ".");
			Patisserie patisserie = new Patisserie(nom, couleur, poids, prix);
			patisserie.setId(id);
			return patisserie;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
