package modele;

public class Patissier {

	protected int id;
	protected String nom;
	protected String courriel;
	protected String age;
	protected String ville;

	protected int id_patisserie;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Patissier(){}
	public Patissier(String nom) {
		super();
		this.nom = nom;
	}
	public Patissier(String nom, String courriel) {
		super();
		this.nom = nom;
		this.courriel = courriel;
	}
	public Patissier(String nom, String courriel, String age) {
		super();
		this.nom = nom;
		this.courriel = courriel;
		this.age = age;
	}
	public Patissier(String nom, String courriel, String age, String ville) {
		super();
		this.nom = nom;
		this.courriel = courriel;
		this.age = age;
		this.ville = ville;
	}
	public Patissier(String nom, String courriel, String age, String ville, int id_patisserie) {
		super();
		this.nom = nom;
		this.courriel = courriel;
		this.age = age;
		this.ville = ville;
		this.id_patisserie = id_patisserie;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getId_patisserie() {
		return id_patisserie;
	}

	public void setId_patisserie(int id_patisserie) {
		this.id_patisserie = id_patisserie;
	}
}
