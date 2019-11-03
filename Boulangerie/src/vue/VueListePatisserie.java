package vue;
import java.util.List;

import action.ControleurPatisserie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Patisserie;

public class VueListePatisserie extends Scene {

	protected GridPane grillePatisserie;
	
	private ControleurPatisserie controleur = null;
	
	private Button actionNaviguerAjouterPatisserie;
	public VueListePatisserie() {
		super(new GridPane(), 400,400);
		grillePatisserie = (GridPane) this.getRoot();
		this.actionNaviguerAjouterPatisserie = new Button("Ajouter une patisserie");
	}
	
	public void afficherListePatisserie(List<Patisserie> listePatisseries) {
		this.grillePatisserie.getChildren().clear();
		
		int numero = 0;
		this.grillePatisserie.add(new Label("Nom"), 0, numero);
		this.grillePatisserie.add(new Label("Prix"), 1, numero);
		for(Patisserie patisserie : listePatisseries) {
			Button actionEditerPatisserie = new Button("Éditer");
			actionEditerPatisserie.setUserData(patisserie.getId());
			System.out.println("PATISSERIE : " + patisserie.getNom() + ", ID=" + patisserie.getId());
			actionEditerPatisserie.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent a) {
					controleur.notifierNaviguerEditerPatisserie(patisserie.getId()); // TODO améliorer ceci pour respecter architecture cible = pas de parametre dans les notifications au controleur
				}});
			numero++;
			this.grillePatisserie.add(new Label(patisserie.getNom()), 0, numero);
			this.grillePatisserie.add(new Label(patisserie.getPrix()), 1, numero);
			this.grillePatisserie.add(actionEditerPatisserie, 2, numero);
		}
		
		this.actionNaviguerAjouterPatisserie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controleur.notifierNaviguerAjouterPatisserie();
			}	
		});
		
		this.grillePatisserie.add(this.actionNaviguerAjouterPatisserie, 0, ++numero);
	}

	public void setControleur(ControleurPatisserie controleur) {
		this.controleur = controleur;
	}
}
