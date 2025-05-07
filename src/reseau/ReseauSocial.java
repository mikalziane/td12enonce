package reseau;

import java.util.HashMap;

public final class ReseauSocial {
	private static ReseauSocial reseau = new ReseauSocial();

	public static ReseauSocial get() {
		return reseau;
	}

	private HashMap<String, Recepteur> abonnes;

	private ReseauSocial() {
		abonnes = new HashMap<String, Recepteur>();
	}

	public void abonner(String id, Recepteur ut) throws Exception {
		assert (ut != null);
		if (abonnes.containsKey(id))
			throw new Exception(id + " est d�ja abonn�");
		abonnes.put(id, ut);
	}

	public void bannir(String id) {
		abonnes.remove(id);
	}

	public void communiquer(String emetteur, String recepteur, String message)
			throws Exception {
		if (!abonnes.containsKey(emetteur))
			throw new Exception(emetteur + " n'est pas abonn�");
		if (!abonnes.containsKey(recepteur))
			throw new Exception(recepteur + " n'est pas abonn�");

		Moderateur.modere(emetteur, message);

		// le moderateur peut avoir banni l'emetteur
		if (!abonnes.containsKey(emetteur))
			throw new Exception(emetteur + " a ete banni");

		abonnes.get(recepteur).recevoir(emetteur, message);
	}
}
