import java.util.ArrayList;

/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 *
 * Gestion d'un terrain.
 * 
 */

public class TouficTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Terrain t = new Terrain(10, 10);

		t.affiche(2);
		System.out.println("Informations sur le terrain:\n" + t + "\n");

		int nbPetrole = randEntre(2, 6);
		for (int i = 0; i < nbPetrole; i++) {
			int x = randEntre(0, 4);
			int y = randEntre(5, 9);
			int qte = randEntre(200, 300); // En litres
            Petrole petrole = new Petrole(qte);
            petrole.setPosition(x, y);
			t.setCase(x, y, petrole);
		}
		t.affiche(7);
		System.out.println("Informations sur le terrain:\n" + t + "\n");

		System.out.println("Nos Techniciens Pétroliers :");
		ArrayList<TechnicienPetrolier> tps = new ArrayList<TechnicienPetrolier>();
		int nbTPs = randEntre(2, 6);
		for (int i = 0; i < nbTPs; i++) {
			int capaciteDeCollecte = randEntre(20, 40);
			int capaciteDeBarril = randEntre(20, 40);
			int nbBarrils = randEntre(4, 7);
			tps.add(new TechnicienPetrolier(capaciteDeCollecte, capaciteDeBarril, nbBarrils, t));
			System.out.println(tps.get(i));
		}

		for (TechnicienPetrolier tp : tps) {
			System.out.println("\nLe Téchnicien numéro " + tp.ident + " parcourt le terrain.");
			for (int i = 0; i < 10 && !tp.estPlein(); i++) {
				for (int j = 0; j < 10 && !tp.estPlein(); j++) {
					if (t.getCase(i, j) instanceof Petrole) {
						Petrole p = (Petrole) t.getCase(i, j);
						System.out.println("J'ai trouvé " + p.getQuantite() + "L de pétrole en (" + p.getX() + ", " + p.getY() + ")");
					}
					tp.seDeplacer(i, j);
					boolean collecteSucces = true;
					while (collecteSucces) {
						StatutReponse collecteResultat = tp.collecter();
						if (collecteResultat.succes) {
							System.out.println(collecteResultat.message);
						}
						collecteSucces = collecteResultat.succes;
					}
				}
			}

			t.affiche(7);
			System.out.println("Informations sur le terrain:\n" + t + "\n");
		}

		System.out.println("Nos Techniciens Pétroliers après extraction :");
		int totalExtraction = 0;
		for (TechnicienPetrolier tp : tps) {
			System.out.println(tp);
			totalExtraction += tp.getQuantiteCollectee();
		}
		System.out.println("Ils ont collecté " + totalExtraction + "L en tout.");

		Usine u = new Usine(t, 20);
		for (TechnicienPetrolier tp : tps) {
			u.deposerPetrole(tp.videCollecte());
		}
		PlastiquePolluant pp = u.produirePlastique();
		System.out.println(pp);
	}

	private static int randEntre(int min, int max) {
		return (int) (Math.random() * Math.abs(max - min) + min);
	}
}
