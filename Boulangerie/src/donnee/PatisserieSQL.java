package donnee;

public interface PatisserieSQL {
	
	public static final String SQL_LISTER_PATISSERIES = "SELECT * FROM patisserie";
	public static final String SQL_AJOUTER_PATISSERIE = "INSERT into patisserie(nom, couleur, poids, prix) VALUES(?,?,?,?)";
	public static final String SQL_MODIFIER_PATISSERIE = "UPDATE patisserie SET nom = ?, couleur = ?, poids = ?, prix = ? WHERE id = ?";
	public static final String SQL_RAPPORTER_PATISSERIE = "SELECT * FROM patisserie WHERE id = ?";

}
