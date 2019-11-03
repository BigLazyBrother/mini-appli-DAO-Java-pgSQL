import donnee.*;
import modele.*;
import vue.*;
import donnee.PatissierDAO;

import java.util.List;

public class App {

	public static void main(String[] parametres) {

		/*
		PatissierDAO accesseurPatissier = new PatissierDAO();

		Patissier patissier1 = new Patissier();
		patissier1.setId(1);

		List<Patissier> listePatissiers = accesseurPatissier.listerPatissier();
		System.out.println("Nous sommes dans App.main(), avant le for");
		for(Patissier patissier : listePatissiers){
			System.out.println(patissier.getNom());
		}
		*/

		NavigateurDesVues.launch(NavigateurDesVues.class, parametres);
		
	}

}
