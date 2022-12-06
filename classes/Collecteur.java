/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 * Interface qui généralise les méthodes communes aux collecteurs.
 * Utilisée par {@TechnicienPetrolier} et {@TravailleurUsine}.
 *
 */

public interface Collecteur {
    public StatutReponse collecter();

    public int getCapaciteDeStockage();

    public int getQuantiteCollectee();

    public boolean estPlein();

    public int videCollecte();
}
