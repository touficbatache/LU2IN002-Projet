/**
 *
 *  Test de la Simulation de l'écosystème créé.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 */

import java.util.ArrayList;

public class TestSimulation {
    public static final int[] terrainSize = { 10, 10 };

    public static void main(String[] args) {
        Terrain terrain = new Terrain(terrainSize[0], terrainSize[1]);
        terrain.affiche(2);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        Simulation u = new Simulation(terrain, 20);

        u.createPetrole();
        
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Techniciens Pétroliers :");
        u.createTechniciens();

        u.runTechniciens();
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Techniciens Pétroliers après extraction :");
        int totalExtraction = 0;
        u.extractionPetrole();
        System.out.println("Ils ont collecté " + totalExtraction + "L en tout.\n");

        System.out.println("L'usine produit du plastique...");
        u.produirePlastique();
        u.setPPS();
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Travailleurs à l'usine :");
        u.createTravailleurs();
        u.runTravailleurs();
        terrain.affiche(7);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");

        System.out.println("Nos Travailleurs à l'usine après ramassage :");
        u.ramassage();
        System.out.println("Ils ont collecté " + u.getTotalRamassage() + "kg de plastique polluant en tout.\n");

        System.out.println("Recyclage en cours...");
        u.recyclerTout();
        u.setPBDS();
        System.out.println("Du plastique biodégradable a été jetté dans l'eau \n");
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");
        terrain.affiche(7);

        u.decomposerTout();

        System.out.println(u.getQteDecomp() + " plastiques biodegradables se sont décomposés \n");
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");
        terrain.affiche(7);
    }
}
