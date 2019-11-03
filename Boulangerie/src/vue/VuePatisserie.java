package vue;
import action.ControleurPatisserie;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Patisserie;

public class VuePatisserie extends Scene{

	protected Label valeurNom;
	protected Label valeurCouleur;
	protected Label valeurPoids;
	protected Label valeurPrix;

	@SuppressWarnings("unused")
	private ControleurPatisserie controleur = null;
	
	public VuePatisserie() {
		super(new GridPane(),400,400);
		GridPane grillePatisserie = (GridPane) this.getRoot();

		// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
		valeurNom = new Label("");
		grillePatisserie.add(new Label("Nom : "), 0, 0);
		grillePatisserie.add(valeurNom, 1, 0);
		
		valeurCouleur = new Label("");
		grillePatisserie.add(new Label("Couleur de glaçage : "), 0, 1);
		grillePatisserie.add(valeurCouleur, 1, 1);

		valeurPoids = new Label("");
		grillePatisserie.add(new Label("Poids : "), 0, 2);
		grillePatisserie.add(valeurPoids, 1, 2);

		valeurPrix = new Label("");
		grillePatisserie.add(new Label("Prix : "), 0, 3);
		grillePatisserie.add(valeurPrix, 1, 3);
	}
	
	public void afficherPatisserie(Patisserie patisserie) {
		this.valeurNom.setText(patisserie.getNom());
		this.valeurCouleur.setText(patisserie.getCouleur());
		this.valeurPoids.setText(patisserie.getPoids());
		this.valeurPrix.setText(patisserie.getPrix());
	}
	
	
	public void setControleur(ControleurPatisserie controleur) {
		this.controleur = controleur;
	}
}
