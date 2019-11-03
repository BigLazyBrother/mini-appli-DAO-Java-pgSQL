package donnee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.Patisserie;
import modele.Patissier;

import static donnee.PatissierSQL.*;


public class PatissierDAO {

	private Connection connection = null;

	public PatissierDAO(){
		this.connection = BaseDeDonnees.getInstance().getConnection();
	}

	public List<Patissier> listerPatissier() {

		List<Patissier> listePatissier = new ArrayList<Patissier>();
		Statement requeteListePatissier;
		try {
			requeteListePatissier = this.connection.createStatement();
			ResultSet curseurPatissier = requeteListePatissier.executeQuery(SQL_LISTER_PATISSIER);

			System.out.println("On est dans la fonction requeteListePatissier, avant le while");

			while(curseurPatissier.next()){
				Patissier patissier = new Patissier();

				int id = curseurPatissier.getInt("id");
				int idPatisserieLiee = curseurPatissier.getInt("id_patisserie");
				String nom = curseurPatissier.getString("nom");
				String courriel = curseurPatissier.getString("courriel");
				String age = curseurPatissier.getString("age");
				String ville = curseurPatissier.getString("ville");

				patissier.setId(id);
				patissier.setId_patisserie(idPatisserieLiee);
				patissier.setNom(nom);
				patissier.setCourriel(courriel);
				patissier.setAge(age);
				patissier.setVille(ville);

				//On ne peut pas afficher tous les patissiers, il faut donc tester
				//quels patissiers sont liés à la patisserie actuelle.
				//if(idPatisserieLiee == ){
				//	listePatissier.add(patissier);
				//	System.out.println("PatissierDAO.requeteListePatissier() → Patissier ajouté !");
				//}
				// MÉTHODE LISTER PATISSIER PAR PATISSERIE FAITE POUR ÇA.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listePatissier;
	}

	public List<Patissier> listerPatissierParPatisserie(Patisserie patisserie){

		List<Patissier> listePatissier = new ArrayList<Patissier>();

		Statement requeteListePatissier;
		try {
			requeteListePatissier = this.connection.createStatement();
			ResultSet curseurPatissier = requeteListePatissier.executeQuery("SELECT * from patissier WHERE id_patisserie = " + patisserie.getId());

			while(curseurPatissier.next()){
				Patissier patissier = new Patissier();

				int id = curseurPatissier.getInt("id");
				//on pourait aussi ajouter l'idPatisserie.
				String nom = curseurPatissier.getString("nom");
				String courriel = curseurPatissier.getString("courriel");
				String age = curseurPatissier.getString("age");
				String ville = curseurPatissier.getString("ville");
				patissier.setId(id);
				patissier.setNom(nom);
				patissier.setCourriel(courriel);
				patissier.setAge(age);
				patissier.setVille(ville);

				listePatissier.add(patissier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listePatissier;
	}

	public List<Patissier> listerPatissierParPatisserie(int idPatisserie){

		List<Patissier> listePatissier = new ArrayList<Patissier>();

		Statement requeteListePatissier;
		try {
			requeteListePatissier = this.connection.createStatement();
			ResultSet curseurPatissier = requeteListePatissier.executeQuery("SELECT * from patissier WHERE id_patisserie = " + idPatisserie);

			while(curseurPatissier.next()){
				Patissier patissier = new Patissier();

				int id = curseurPatissier.getInt("id");
				//on pourait aussi ajouter l'idPatisserie.
				String nom = curseurPatissier.getString("nom");
				String courriel = curseurPatissier.getString("courriel");
				String age = curseurPatissier.getString("age");
				String ville = curseurPatissier.getString("ville");
				patissier.setId(id);
				patissier.setNom(nom);
				patissier.setCourriel(courriel);
				patissier.setAge(age);
				patissier.setVille(ville);

				listePatissier.add(patissier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listePatissier;
	}

	public void ajouterPatissier(Patissier patissier, int idPatisserie) {

		System.out.println("nous sommes dans PatissierDAO.ajouterPatissier(Patissier p)");
		try {
			PreparedStatement requeteAjouterPatisserie = connection.prepareStatement(SQL_AJOUTER_PATISSIER);
			requeteAjouterPatisserie.setString(1, patissier.getNom());
			requeteAjouterPatisserie.setString(2, patissier.getCourriel());
			requeteAjouterPatisserie.setString(3, patissier.getAge());
			requeteAjouterPatisserie.setString(4, patissier.getVille());
			requeteAjouterPatisserie.setInt(5, idPatisserie);

			//pout tester, tant que l'id n'est pas transmise.
			//System.out.println("Patissier ajouté à la patisserie 1 : Patte d'ours");

			System.out.println("SQL : " + SQL_AJOUTER_PATISSIER);
			requeteAjouterPatisserie.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Patissier rapporterPatissier(int idPatissier) {
		PreparedStatement requetePatissier;
		try {
			requetePatissier = connection.prepareStatement(SQL_RAPPORTER_PATISSIER);
			requetePatissier.setInt(1, idPatissier);
			System.out.println(SQL_RAPPORTER_PATISSIER + "idPatissier=" + idPatissier);
			ResultSet curseurPatissier = requetePatissier.executeQuery();
			curseurPatissier.next();
			int id = curseurPatissier.getInt("id");
			int idPatisserieLiee = curseurPatissier.getInt("id_patisserie");
			String nom = curseurPatissier.getString("nom");
			String courriel = curseurPatissier.getString("courriel");
			String age = curseurPatissier.getString("age");
			String ville = curseurPatissier.getString("ville");
			System.out.println("Patissier « " + nom + " » courriel : " + courriel + ", a " + age + " ans et habite " + ville + ".");
			Patissier patissier = new Patissier(nom, courriel, age, ville);
			patissier.setId(id);
			patissier.setId_patisserie(idPatisserieLiee);
			return patissier;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void modifierPatissier(Patissier patissier) {
		System.out.println("PatissierDAO.modifierPatissier()");
		try {
			// "UPDATE patissier SET nom = ?, courriel = ?, age = ?, ville = ?, id_patisserie = ? WHERE id = ?";
			PreparedStatement requeteModifierPatissier = connection.prepareStatement(SQL_MODIFIER_PATISSIER);
			requeteModifierPatissier.setString(1, patissier.getNom());
			requeteModifierPatissier.setString(2, patissier.getCourriel());
			requeteModifierPatissier.setString(3, patissier.getAge());
			requeteModifierPatissier.setString(4, patissier.getVille());
			requeteModifierPatissier.setInt(5, patissier.getId_patisserie());
			requeteModifierPatissier.setInt(6, patissier.getId());

			//System.out.println("PATISSIER ..........NOM=" + patissier.getNom());
			//System.out.println("PATISSIER .....COURRIEL=" + patissier.getCourriel());
			//System.out.println("PATISSIER ..........AGE=" + patissier.getAge());
			//System.out.println("PATISSIER ........VILLE=" + patissier.getVille());
			//System.out.println("PATISSIER ID_PATISSERIE=" + patissier.getId_patisserie());
			//System.out.println("PATISSIER ...........ID=" + patissier.getId());

			//System.out.println("SQL : " + SQL_MODIFIER_PATISSIER);
			requeteModifierPatissier.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



