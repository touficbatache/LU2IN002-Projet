/**
 *
 * Généralisation des méthodes communes aux collecteurs.
 * Utilisée par {@link TechnicienPetrolier} et {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 */

public interface Collecteur {
    StatutReponse collecter();

    int getCapaciteDeStockage();

    int getQuantiteCollectee();

    boolean estPlein();

    int videCollecte();
}
