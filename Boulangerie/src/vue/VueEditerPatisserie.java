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
import javafx.scene.shape.Line;
import modele.Patisserie;
import modele.Patissier;

import java.util.List;

public class VueEditerPatisserie extends Scene {

	protected TextField valeurNom;
	protected TextField valeurCouleur;
	protected TextField valeurPoids;
	protected TextField valeurPrix;
	
	private ControleurPatisserie controleur = null;
	protected Button actionEnregistrerPatisserie = null;
	protected Button actionAjouterPatissier= null;
	
	private int idPatisserie = 0;
	private GridPane grillePatissier = null; //new GridPane();
	
	public VueEditerPatisserie()  {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();
		GridPane grillePatisserie = new GridPane();
		this.grillePatissier = new GridPane();
		this.actionEnregistrerPatisserie = new Button("Enregistrer");
		
		this.actionEnregistrerPatisserie.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				controleur.notifierEnregistrerPatisserie();
				
			}});

		this.actionAjouterPatissier = new Button("Ajouter un patissier");
		this.actionAjouterPatissier.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controleur.notifierNaviguerAjouterPatissier();
			}
		});
		
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
		panneau.getChildren().add(new Label("Éditer une patisserie")); // Todo : créer un sous-type de Label ou Text pour les titres
		panneau.getChildren().add(grillePatisserie);
		panneau.getChildren().add(this.actionEnregistrerPatisserie);

		Line separateur = new Line();
		separateur.setStartX(0.0f);
		separateur.setEndX(400.0f);
		panneau.getChildren().add(separateur);

		panneau.getChildren().add(new Label("Liste des patissiers"));
		panneau.getChildren().add(grillePatissier);
		panneau.getChildren().add(this.actionAjouterPatissier);
	}
	
	public void afficherPatisserie(Patisserie patisserie) {
		this.idPatisserie = patisserie.getId();

		this.valeurNom.setText(patisserie.getNom());
		this.valeurCouleur.setText(patisserie.getCouleur());
		this.valeurPoids.setText(patisserie.getPoids());
		this.valeurPrix.setText(patisserie.getPrix());
	}
		
	public Patisserie demanderPatisserie() {
		Patisserie patisserie = new Patisserie(this.valeurNom.getText(),
								this.valeurCouleur.getText(), 
								this.valeurPoids.getText(), 
								this.valeurPrix.getText());
		patisserie.setId(idPatisserie);
		return patisserie;
	}

	public void setControleur(ControleurPatisserie controleur) {
		this.controleur = controleur;
	}

	public int getIdPatissierVueEditerPatisserie() {
		return idPatisserie;
	} //ATTENTION ! LES NOMS SEMBLENT CONFONDUS !

	public void afficherListePatissier(List<Patissier> listePatissiers){
		//this.grillePatissier.add(new Label("Test"),0,0);
		//System.out.println("Nous sommes dans VueEditerPatisserie.afficherListePatissier() avant le for");
		this.grillePatissier.getChildren().clear();
		int rangee = 0;
		for (Patissier patissier : listePatissiers ) {
			int idPatissierActuel = patissier.getId();
			System.out.println("PATISSIER : " + patissier.getNom() + ", ID=" + idPatissierActuel);
			this.grillePatissier.add(new Label(patissier.getNom()),0,rangee); //On ne change pas la valeur avant d'avoir ajouté le bouton
			Button actionEditerPatissier = new Button("Éditer");
			actionEditerPatissier.setUserData(idPatissierActuel);
			actionEditerPatissier.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent a) {
					controleur.notifierNaviguerEditerPatissier(idPatissierActuel); // TODO améliorer ceci pour respecter architecture cible = pas de parametre dans les notifications au controleur
				}});
			this.grillePatissier.add(actionEditerPatissier, 2, rangee++);
		}
	}
}
