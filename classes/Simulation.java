import java.util.ArrayList;

public class Simulation {
    public static final int[] terrainSize = {10, 10};
    
    public static void main(String[] args) {
        Terrain terrain = new Terrain(terrainSize[0], terrainSize[1]);
        terrain.affiche(2);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        Usine u = new Usine(terrain, 20);

        int nbPetrole = randEntre(2, 6);
		for (int i = 0; i < nbPetrole; i++) {
			int x = randEntre(0, 4);
			int y = randEntre(5, 9);
			int qte = randEntre(200, 300); // En litres
            Petrole petrole = new Petrole(qte);
            petrole.setPosition(x, y);
			terrain.setCase(x, y, petrole);
		}
		terrain.affiche(7);
		System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Techniciens Pétroliers :");
		ArrayList<TechnicienPetrolier> tps = new ArrayList<TechnicienPetrolier>();
		int nbTPs = randEntre(2, 6);
		for (int i = 0; i < nbTPs; i++) {
			int capaciteDeCollecte = randEntre(20, 40);
			int capaciteDeBarril = randEntre(20, 40);
			int nbBarrils = randEntre(3, 7);
			tps.add(new TechnicienPetrolier(capaciteDeCollecte, capaciteDeBarril, nbBarrils, terrain));
			System.out.println(tps.get(i));
		}
        u.addTechniciens(tps);
        u.runTechniciens();
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

		System.out.println("Nos Techniciens Pétroliers après extraction :");
		int totalExtraction = 0;
		for (TechnicienPetrolier tp : u.getTechniciens()) {
			System.out.println(tp);
			totalExtraction += tp.getQuantiteCollectee();
		}
		System.out.println("Ils ont collecté " + totalExtraction + "L en tout.\n");
        
        System.out.println("L'usine produit du plastique...");
		PlastiquePolluant pp = u.produirePlastique();
        int xPP = randEntre(5, 9);
        int yPP = randEntre(0, 4);
        pp.setPosition(xPP, yPP);
        System.out.println(pp);
        terrain.setCase(xPP, yPP, pp);
		terrain.affiche(7);
		System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Travailleurs à l'usine :");
		ArrayList<TravailleurUsine> tus = new ArrayList<TravailleurUsine>();
		int nbTUs = randEntre(1, 2);
		for (int i = 0; i < nbTUs; i++) {
			int capaciteDeCollecte = randEntre(1, 1);
			int capaciteDeStockage = randEntre(3, 5);
			tus.add(new TravailleurUsine(capaciteDeCollecte, capaciteDeStockage, terrain));
			System.out.println(tus.get(i));
		}
        u.addTravailleurs(tus);
        u.runTravailleurs();
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Travailleurs à l'usine après ramassage :");
		int totalRamassage = 0;
		for (TravailleurUsine tu : u.getTravailleurs()) {
			System.out.println(tu);
			totalRamassage += tu.getQuantiteCollectee();
		}
		System.out.println("Ils ont collecté " + totalRamassage + "kg de plastique polluant en tout.\n");

        u.recyclerTout();
    }

    private static int randEntre(int min, int max) {
		return (int) (Math.random() * Math.abs(max - min) + min);
	}
}
