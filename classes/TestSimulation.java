/**
 * Test de la Simulation de l'écosystème créé.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class TestSimulation {
    public static void main(String[] args) {
        // Instance de classe Simulation
        Simulation simulation = Simulation.getInstance();

        // Ajouter le pétrole au terrain
        simulation.createPetrole();

        // Affiche terrain
        simulation.afficheTerrain(7);

        // Crée et affiche les Techniciens Pétroliers
        simulation.createTechniciens(true);

        // Crée et affiche les Travailleurs d'usine
        simulation.createTravailleurs(true);

        for (int i = 1; i < 15; i++) {
            if (simulation.getCasesRemplies() == 0) {
                System.out.println("L'écosystème s'est éteint au bout de " + i + " cycles");
                break;
            }

            System.out.println("|---------------------------------------------------|");
            System.out.println("|--------------------  Cycle " + i + "  --------------------|");
            System.out.println("|---------------------------------------------------|");

            // Parcourir et extraire le pétrole
            simulation.runTechniciens();

            try {
                // Vider les extractions de pétrole, produire du plastique et polluer le terrain
                System.out.println("Nos Techniciens Pétroliers après extraction :");
                simulation.afficheTechniciens();
                int totalExtraction = simulation.videExtractionPetrole();
                System.out.println("Ils ont collecté " + totalExtraction + "L en tout.\n");
                System.out.println("L'usine produit du plastique...");
                simulation.produirePlastique();
                simulation.polluer(false);
                System.out.println("Du plastique polluant a été jeté dans l'eau\n");
            } catch (PasDeCollecteException e) {
                System.out.println(e.getMessage());
            }

            // Affiche terrain
            simulation.afficheTerrain(7);
            // Parcourir et collecter le plastique polluant
            simulation.runTravailleurs();

            try {
                // Vider les ramassages de plastique, tout recycler et les laisser se décomposer dans le terrain
                System.out.println("Nos Travailleurs à l'usine après ramassage :");
                int totalRamassage = simulation.videRamassagePlastique();
                System.out.println("Ils ont collecté " + totalRamassage + "kg de plastique polluant en tout.\n");
                System.out.println("Recyclage en cours...");
                int nbPlastiquesBio = simulation.recyclerTout();
                simulation.rejettePlastiquesBio(true);
                System.out.println(nbPlastiquesBio + " plastiques biodégradables ont été jetés dans l'eau\n");
            } catch (PasDeCollecteException e) {
                System.out.println(e.getMessage());
            }

//            if (nbPlastiquesBio != null) {
//                // Affiche terrain
//                simulation.afficheTerrain(7);
//
//                // Appelle jusqu'à ce qu'il n'y ait plus aucun plastique
//                while (simulation.getQteDecomposee() != Integer.parseInt(nbPlastiquesBio)) {
//                    // Essaye de décomposer le plastique biodégradable jeté dans l'eau
//                    simulation.decomposerTout();
//                    System.out.println(simulation.getQteDecomposee() + " plastiques biodégradables se sont décomposés \n");
//                    // Affiche terrain
//                    simulation.afficheTerrain(7);
//                }
//            }

            // Affiche terrain
            simulation.afficheTerrain(7);
            // Essaye de décomposer le plastique biodégradable jeté dans l'eau
            int qteDecomposee = simulation.decomposerTout();
            System.out.println(qteDecomposee + " plastiques biodégradables se sont décomposés \n");
            // Affiche terrain
            simulation.afficheTerrain(7);

            simulation.augmenteAge();
        }
    }
}
