package modele;

public class Patisserie {

	protected int id;
	protected String nom;
	protected String couleur;
	protected String poids;
	protected String prix;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patisserie(String nom) {
		super();
		this.nom = nom;
	}
	public Patisserie(String nom, String couleur) {
		super();
		this.nom = nom;
		this.couleur = couleur;
	}
	public Patisserie(String nom, String couleur, String poids) {
		super();
		this.nom = nom;
		this.couleur = couleur;
		this.poids = poids;
	}
	public Patisserie(String nom, String couleur, String poids, String prix) {
		super();
		this.nom = nom;
		this.couleur = couleur;
		this.poids = poids;
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}
}
