package donnee;

public interface PatissierSQL {
	
	public static final String SQL_LISTER_PATISSIER = "SELECT * FROM patissier";
	public static final String SQL_AJOUTER_PATISSIER = "INSERT into patissier(nom, courriel, age, ville, id_patisserie) VALUES(?,?,?,?,?)";
	public static final String SQL_MODIFIER_PATISSIER = "UPDATE patissier SET nom = ?, courriel = ?, age = ?, ville = ?, id_patisserie = ? WHERE id = ?";
	public static final String SQL_RAPPORTER_PATISSIER = "SELECT * FROM patissier WHERE id = ?";

}
