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
import modele.Patissier;

public class VueAjouterPatissier extends Scene {

    /*
    private ControleurPatisserie controleur = null;

    public VueAjouterPatissier() throws IOException {
        super(FXMLLoader.load(VueAjouterPatissier.class.getResource("ajouter-patissier.fxml")));
    }

    public void setControleur(ControleurPatisserie controleur) {
        this.controleur = controleur;
    }

    public Patissier demanderPatissier() {
        Patissier patissier = new Patissier();
        patissier.setNom(((TextField)this.lookup("#champs-nom")).getText());
        patissier.setCourriel(((TextField)this.lookup("#champs-courriel")).getText());
        patissier.setAge(((TextField)this.lookup("#champs-age")).getText());
        patissier.setVille(((TextField)this.lookup("#champs-ville")).getText());
        return patissier;
    }
    */

    protected TextField valeurNom;
    protected TextField valeurCourriel;
    protected TextField valeurAge;
    protected TextField valeurVille;

    private ControleurPatisserie controleur = null;
    protected Button actionEnregistrerPatisserie = null;

    public VueAjouterPatissier()  {
        super(new VBox(), 400, 400);
        VBox panneau = (VBox) this.getRoot();
        GridPane grillePatisserie = new GridPane();
        this.actionEnregistrerPatisserie = new Button("Enregistrer");

        this.actionEnregistrerPatisserie.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                controleur.notifierEnregistrerAjoutPatissier();

            }});

        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
        valeurNom = new TextField("");
        grillePatisserie.add(new Label("Nom : "), 0, 0);
        grillePatisserie.add(valeurNom, 1, 0);

        valeurCourriel = new TextField("");
        grillePatisserie.add(new Label("Courriel : "), 0, 1);
        grillePatisserie.add(valeurCourriel, 1, 1);

        valeurAge = new TextField("");
        grillePatisserie.add(new Label("Age : "), 0, 2);
        grillePatisserie.add(valeurAge, 1, 2);

        valeurVille = new TextField("");
        grillePatisserie.add(new Label("Ville : "), 0, 3);
        grillePatisserie.add(valeurVille, 1, 3);

        // Todo : retirer les textes magiques
        panneau.getChildren().add(new Label("Ajouter un patissier")); // Todo : créer un sous-type de Label ou Text pour les titres
        panneau.getChildren().add(grillePatisserie);
        panneau.getChildren().add(this.actionEnregistrerPatisserie);
    }

    public Patissier demanderPatissier() {
        Patissier patissier = new Patissier(
                this.valeurNom.getText(),
                this.valeurCourriel.getText(),
                this.valeurAge.getText(),
                this.valeurVille.getText());
        return patissier;
    }

    public void setControleur(ControleurPatisserie controleur) {
        this.controleur = controleur;
    }
}
