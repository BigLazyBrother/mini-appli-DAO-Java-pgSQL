package action;

import java.util.List;

import donnee.PatisserieDAO;
import donnee.PatissierDAO;
import modele.Patisserie;
import modele.Patissier;
import vue.*;

public class ControleurPatisserie {

	private PatissierDAO accesseurPatissier = new PatissierDAO();
	private NavigateurDesVues navigateur;
	private VueListePatisserie vueListePatisserie = null;
	private VuePatisserie vuePatisserie = null;
	private VueAjouterPatisserie vueAjouterPatisserie = null;
	private VueAjouterPatissier vueAjouterPatissier = null;
	private VueEditerPatisserie vueEditerPatisserie = null;
	private PatisserieDAO patisserieDAO = null;
	private PatissierDAO patissierDAO = null;
	private VueEditerPatissier vueEditerPatissier = null;
	
	private ControleurPatisserie() {
		System.out.println("Initialisation du contrôleur");
		this.patisserieDAO = new PatisserieDAO();
		this.patissierDAO = new PatissierDAO();
		this.navigateur = NavigateurDesVues.getInstance();
	}
	
	public void activerVues(NavigateurDesVues navigateur) {
		this.navigateur = navigateur;
		//this.vueAjouterPatisserie = navigateur.getVueAjouterPatisserie();
		this.vuePatisserie = navigateur.getVuePatisserie();
		this.vueListePatisserie = navigateur.getVueListePatisserie();
		this.vueEditerPatisserie = navigateur.getVueEditerPatisserie();
		this.vueAjouterPatissier = navigateur.getVueAjouterPatissier();
		this.vueEditerPatissier = navigateur.getVueEditerPatissier();
						
		//// TEST ////
		Patisserie patisserie = new Patisserie("Patte d'ours", "brun", "120", "3"); //PostGreSQL gère les apostrophes !!!
		this.vuePatisserie.afficherPatisserie(patisserie); // Appel de ma fonction avant de la programmer (pour tester à mesure)
		
		this.navigateur.naviguerVersVuePatisserie();
		
		/// TEST ///
		List<Patisserie> listePatisserieTest = patisserieDAO.listerPatisseries();
		this.vueListePatisserie.afficherListePatisserie(listePatisserieTest); // Appel de ma fonction avant de la programmer (pour tester à mesure)
		
		this.navigateur.naviguerVersVueListePatisserie();
		//this.navigateur.naviguerVersVueAjouterPatisserie();
		//this.vueEditerPatisserie.afficherListeDistinction(this.distinctionDAO.listerDistinctions());
	}
	
	// SINGLETON DÉBUT
	private static ControleurPatisserie instance = null;
	public static ControleurPatisserie getInstance() {
		if(null == instance) instance = new ControleurPatisserie();
		return instance;
	}
	// SINGLETON FIN

	
	



	////////////////////////////////////////////////////////////////////////
	//                                                                    //
	//                           NOTIFICATIONS                            //
	//                                                                    //
	////////////////////////////////////////////////////////////////////////
	
	// Les notifications peuvent être gérées par callback comme ici ou par événement,
	// Mais dans les deux cas les opérations sont divisées dans des fonctions comme ici
	// Pas de code dans un switch - case
	
	public void notifierEnregistrerNouvellePatisserie() {
		System.out.println("ControleurPatisserie.notifierEnregistrerNouvellePatisserie()");
		Patisserie patisserie = this.navigateur.getVueAjouterPatisserie().demanderPatisserie();
		this.patisserieDAO.ajouterPatisserie(patisserie);
		this.vueListePatisserie.afficherListePatisserie(this.patisserieDAO.listerPatisseries()); // TODO optimiser
		this.navigateur.naviguerVersVueListePatisserie();
	}
	
	public void notifierEnregistrerPatisserie() {
		System.out.println("ControleurPatisserie.notifierEnregistrerPatisserie()");
		Patisserie patisserie = this.navigateur.getVueEditerPatisserie().demanderPatisserie();
		this.patisserieDAO.modifierPatisserie(patisserie);
		this.vueListePatisserie.afficherListePatisserie(this.patisserieDAO.listerPatisseries()); // TODO optimiser
		this.navigateur.naviguerVersVueListePatisserie();
	}

	public void notifierNaviguerAjouterPatisserie()	{
		System.out.println("ControleurPatisserie.notifierNaviguerAjouterPatisserie()");
		this.navigateur.naviguerVersVueAjouterPatisserie();
	}
	
	public void notifierNaviguerEditerPatisserie(int idPatisserie) {
		System.out.println("ControleurPatisserie.notifierEditerPatisserie("+idPatisserie+")");
		// savoir par la vue liste quel numero de patisserie a ete clique
		Patisserie patisserie = this.patisserieDAO.rapporterPatisserie(idPatisserie);
		this.vueEditerPatisserie.afficherPatisserie(this.patisserieDAO.rapporterPatisserie(idPatisserie));
		this.navigateur.naviguerVersVueEditerPatisserie();

		List<Patissier> listePatissiers = accesseurPatissier.listerPatissierParPatisserie(patisserie);

		//TEST
		this.vueEditerPatisserie.afficherListePatissier(listePatissiers);
	}

	public void notifierNaviguerAjouterPatissier() {
		this.navigateur.naviguerVersVueAjouterPatissier();
	}

    public void notifierNaviguerEditerPatissier(int idPatissier) {
	    System.out.println("ControleurPatissier.notifierEditerPatissier("+idPatissier+")");
	    Patissier patissier = this.patissierDAO.rapporterPatissier(idPatissier);
        this.vueEditerPatissier.afficherPatissier(this.patissierDAO.rapporterPatissier(idPatissier));
	    this.navigateur.naviguerVersVueEditerPatissier(); }

    /**
     * Appel sans fxml (en tous cas pour le moment)
     */
    public void notifierEnregistrerAjoutPatissier() {
        System.out.println("Nous sommes dans ControleurPatisserie.notifierEnregistrerAjoutPatissier()");

		Patissier patissier = this.navigateur.getVueAjouterPatissier().demanderPatissier(); //on récup les infos entrées par l'utilisateur
		this.patissierDAO.ajouterPatissier(patissier, vueEditerPatisserie.getIdPatissierVueEditerPatisserie()); //on les applique dans la BDD

		//on enregistre la patisserie pour pourvoir mettre à jour la liste des patissiers liés
		//Patisserie patisserie = this.navigateur.getVueEditerPatisserie().demanderPatisserie();
		//this.patisserieDAO.modifierPatisserie(patisserie);

		int idPatisserie = this.navigateur.getVueEditerPatissier().demanderIdPatisserieLiee();

        this.vueEditerPatisserie.afficherListePatissier(this.patissierDAO.listerPatissierParPatisserie(idPatisserie)); //on raffraichit la liste des patissiers.
		this.navigateur.naviguerVersVueEditerPatisserie(); //on retourne à la page où on était avant la modif.
    }

    public void notifierEnregistrerPatissier() {
        System.out.println("Nous sommes dans ControleurPatisserie.notifierEnregistrerPatissier()");

        Patissier patissier = this.navigateur.getVueEditerPatissier().demanderPatissier(); //on récup les infos entrées par l'utilisateur
        this.patissierDAO.modifierPatissier(patissier); //on les applique dans la BDD

		//on enregistre la patisserie pour pourvoir mettre à jour la liste des patissiers liés
		//Patisserie patisserie = this.navigateur.getVueEditerPatisserie().demanderPatisserie();
		//this.patisserieDAO.modifierPatisserie(patisserie);

		int idPatisserie = this.navigateur.getVueEditerPatissier().demanderIdPatisserieLiee();

		this.vueEditerPatisserie.afficherListePatissier(this.patissierDAO.listerPatissierParPatisserie(idPatisserie)); //on raffraichit la liste des patissiers.
        this.navigateur.naviguerVersVueEditerPatisserie(); //on retourne à la page où on était avant la modif.
    }
}
