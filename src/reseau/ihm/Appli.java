package reseau.ihm;

import reseau.*;

public class Appli {
	public static void main(String[] args) throws Exception {
		Utilisateur toto = new Utilisateur("toto");
		Utilisateur titi = new Utilisateur("titi");

		toto.prendAbonnement();
		titi.prendAbonnement();

		try {
			// toto parle trop, il va se faire bannir
			for (int i = 0; i < 10; i++)
				toto.emettre("titi", "je parle pour ne rien dire");
		} catch (Exception e) {
			// c'est fait
			System.out.println(e.getMessage());
		}

		// un peu d'ultra-violence
		ReseauSocial.get().abonner("marcel", new Recepteur() {
			public void recevoir(String emetteur, String message) {
				System.out.println("on me cause ?");
			}
		});
		// marcel peut recevoir
		titi.emettre("marcel", "je te cause");
		// marcel peut aussi emettre
		ReseauSocial.get()
				.communiquer("marcel", "titi", "moi aussi je cause !");
	}
}
