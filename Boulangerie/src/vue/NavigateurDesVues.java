package vue;

import java.io.IOException;

import action.ControleurPatisserie;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application{
	
	private Stage stade;
	
	private VueListePatisserie vueListePatisserie = null;
	private VuePatisserie vuePatisserie = null;
	private VueAjouterPatisserie vueAjouterPatisserie = null;
	private VueEditerPatisserie vueEditerPatisserie = null;
	private VueAjouterPatissier vueAjouterPatissier = null;
	private VueEditerPatissier vueEditerPatissier = null;
	
	private ControleurPatisserie controleur = null;

	static private NavigateurDesVues instance = null;
	public NavigateurDesVues() {
		NavigateurDesVues.instance = this; //static, pas de this.
		this.vueListePatisserie = new VueListePatisserie();
		this.vuePatisserie = new VuePatisserie();
		this.vueAjouterPatisserie = new VueAjouterPatisserie();
		this.vueEditerPatisserie = new VueEditerPatisserie();
		this.vueAjouterPatissier = new VueAjouterPatissier();
		this.vueEditerPatissier = new VueEditerPatissier();
	}

	static public NavigateurDesVues getInstance()
	{
		return instance;
	}

	@Override
	public void start(Stage stade) throws Exception {
		this.stade = stade;
		
		this.stade.setScene(null);
		this.stade.show();
	
		this.controleur = ControleurPatisserie.getInstance();
		this.controleur.activerVues(this);
		this.vueListePatisserie.setControleur(controleur);
		this.vuePatisserie.setControleur(controleur);
		this.vueAjouterPatisserie.setControleur(controleur);
		this.vueEditerPatisserie.setControleur(controleur);
		this.vueAjouterPatissier.setControleur(controleur);
		this.vueEditerPatissier.setControleur(controleur);
	}	
	
	public VueListePatisserie getVueListePatisserie() {
		return vueListePatisserie;
	}
	public VuePatisserie getVuePatisserie() {
		return vuePatisserie;
	}
	public VueAjouterPatisserie getVueAjouterPatisserie() {
		return vueAjouterPatisserie;
	}
	public VueEditerPatisserie getVueEditerPatisserie(){
		return this.vueEditerPatisserie;
	}
	public VueAjouterPatissier getVueAjouterPatissier(){
		return this.vueAjouterPatissier;
	}
	public VueEditerPatissier getVueEditerPatissier() { return this.vueEditerPatissier; }
	
	public void naviguerVersVuePatisserie() {
		stade.setScene(this.vuePatisserie);
		stade.show();
	}
	public void naviguerVersVueListePatisserie() {
		stade.setScene(this.vueListePatisserie);
		stade.show();		
	}
	public void naviguerVersVueAjouterPatisserie() {
		stade.setScene(this.vueAjouterPatisserie);
		stade.show();				
	}
	public void naviguerVersVueEditerPatisserie() {
		stade.setScene(this.vueEditerPatisserie);
		stade.show();				
	}
	public void naviguerVersVueAjouterPatissier() {
		stade.setScene(this.vueAjouterPatissier);
		stade.show();
	}
	public void naviguerVersVueEditerPatissier() {
		stade.setScene(this.vueEditerPatissier);
		stade.show();
	}
}
