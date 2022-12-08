/**
 * Généralisation des méthodes communes aux collecteurs.
 * Utilisée par {@link TechnicienPetrolier} et {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public interface Collecteur {
    int collecter() throws Exception;

    /**
     * @return la capacité de stockage
     */
    int getCapaciteDeStockage();

    /**
     * @return la quantité collectée
     */
    int getQuantiteCollectee();

    /**
     * @return si la quantité collectée est supérieure ou égale à la capacité de stockage
     */
    boolean estPlein();

    /**
     * Retourne la quantité collectée et la réinitialise
     *
     * @return la quantité collectée
     */
    int videCollecte();
}
