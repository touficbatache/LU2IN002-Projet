/**
 * Généralisation des méthodes communes aux collecteurs.
 * Utilisée par {@link TechnicienPetrolier} et {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public interface Collecteur {
    StatutReponse collecter();

    /**
     * Permet d'avoir accès à la quantité de stockage
     *
     * @return la capacité de stockage
     */
    int getCapaciteDeStockage();

    /**
     * Permet d'avoir accès à la quantité collectée
     *
     * @return la quantité collectée
     */
    int getQuantiteCollectee();

    /**
     * Renvoie si la quantité collectée est supérieure ou égale à la capacité de stockage
     */
    boolean estPlein();

    /**
     * Reinitialise la quantité collectée
     *
     * @return la quantité collecté
     */
    int videCollecte();
}
