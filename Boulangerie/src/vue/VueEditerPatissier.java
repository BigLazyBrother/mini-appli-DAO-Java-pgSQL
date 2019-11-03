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
import modele.Patissier;

public class VueEditerPatissier extends Scene {

    protected TextField valeurNom;
    protected TextField valeurCourriel;
    protected TextField valeurAge;
    protected TextField valeurVille;

    private ControleurPatisserie controleur = null;
    protected Button actionEnregistrerPatissier = null;

    private int idPatissier = 0;
    private int idPatisserieLiee = 0;
    private GridPane grillePatissier = null; //new GridPane();

    /**
     * Il est nécessaire d'instancier de controleur. Sinon, comme le controleur est null :
     * NULL POINTER EXCEPTION.
     * @param controleur
     */
    public void setControleur(ControleurPatisserie controleur) {
        this.controleur = controleur;
    }

    public VueEditerPatissier()  {
        super(new VBox(), 400, 400);
        VBox panneau = (VBox) this.getRoot();
        //GridPane grillePatissier = new GridPane();
        this.grillePatissier = new GridPane();
        this.actionEnregistrerPatissier = new Button("Enregistrer");

        this.actionEnregistrerPatissier.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                controleur.notifierEnregistrerPatissier();

            }});

        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
        valeurNom = new TextField();
        grillePatissier.add(new Label("Nom : "), 0, 0);
        grillePatissier.add(valeurNom, 1, 0);

        valeurCourriel = new TextField("");
        grillePatissier.add(new Label("Courriel : "), 0, 1);
        grillePatissier.add(valeurCourriel, 1, 1);

        valeurAge = new TextField("");
        grillePatissier.add(new Label("Age : "), 0, 2);
        grillePatissier.add(valeurAge, 1, 2);

        valeurVille = new TextField("");
        grillePatissier.add(new Label("Ville : "), 0, 3);
        grillePatissier.add(valeurVille, 1, 3);

        // Todo : retirer les textes magiques
        panneau.getChildren().add(new Label("Éditer un patissier")); // Todo : créer un sous-type de Label ou Text pour les titres
        panneau.getChildren().add(grillePatissier);
        panneau.getChildren().add(this.actionEnregistrerPatissier);
    }

    public void afficherPatissier(Patissier patissier) {
        System.out.println("VueEditerPatissier.afficherPatissier() : idPatisserieLiee=" + patissier.getId_patisserie());

        this.idPatissier = patissier.getId();
        this.idPatisserieLiee = patissier.getId_patisserie();

        this.valeurNom.setText(patissier.getNom());
        this.valeurCourriel.setText(patissier.getCourriel());
        this.valeurAge.setText(patissier.getAge());
        this.valeurVille.setText(patissier.getVille());
    }

    public Patissier demanderPatissier() {
        Patissier patissier = new Patissier(
                this.valeurNom.getText(),
                this.valeurCourriel.getText(),
                this.valeurAge.getText(),
                this.valeurVille.getText());
        patissier.setId(idPatissier);
        patissier.setId_patisserie(idPatisserieLiee);
        return patissier;
    }

    public int demanderIdPatisserieLiee() {
        return idPatisserieLiee;
    }
}
