package donnee;

public interface Acces {

	static final String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	static final String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/boulangerie";
	static final String BASEDEDONNEES_USAGER = "userLambda";
	static final String BASEDEDONNEES_MOTDEPASSE = "datenbankschlussel";
	// datenbankschlüssel == clé de la base de données. reste à savoir si c'est une clé primaire, étrangère ou le mot de passe XD
	// Ich schätze, dass Deutsch in Amerika nicht sehr verbreitet ist, also ist es gut für Passwörter ??
	
}
