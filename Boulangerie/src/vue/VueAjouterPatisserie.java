package vue;
import action.ControleurPatisserie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Patisserie;

public class VueAjouterPatisserie extends Scene {

	protected TextField valeurNom;
	protected TextField valeurCouleur;
	protected TextField valeurPoids;
	protected TextField valeurPrix;
	
	private ControleurPatisserie controleur = null;
	protected Button actionEnregistrerPatisserie = null;
	
	public VueAjouterPatisserie() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();
		GridPane grillePatisserie = new GridPane();
		this.actionEnregistrerPatisserie = new Button("Enregistrer");
		
		this.actionEnregistrerPatisserie.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				controleur.notifierEnregistrerNouvellePatisserie();
				
			}});
		
		// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
		valeurNom = new TextField();
		grillePatisserie.add(new Label("Nom : "), 0, 0);
		grillePatisserie.add(valeurNom, 1, 0);
		
		valeurCouleur = new TextField("");
		grillePatisserie.add(new Label("Couleur de glaçage : "), 0, 1);
		grillePatisserie.add(valeurCouleur, 1, 1);

		valeurPoids = new TextField("");
		grillePatisserie.add(new Label("Poids : "), 0, 2);
		grillePatisserie.add(valeurPoids, 1, 2);

		valeurPrix = new TextField("");
		grillePatisserie.add(new Label("Prix : "), 0, 3);
		grillePatisserie.add(valeurPrix, 1, 3);
			
		// Todo : retirer les textes magiques
		panneau.getChildren().add(new Label("Ajouter une patisserie")); // Todo : créer un sous-type de Label ou Text pour les titres
		panneau.getChildren().add(grillePatisserie);
		panneau.getChildren().add(this.actionEnregistrerPatisserie);
	}
	
	public Patisserie demanderPatisserie() {
		Patisserie patisserie = new Patisserie(this.valeurNom.getText(),
								this.valeurCouleur.getText(),
								this.valeurPoids.getText(), 
								this.valeurPrix.getText());
		return patisserie;
	}
	
	public void setControleur(ControleurPatisserie controleur) {
		this.controleur = controleur;
	}
}
