/**
 * Généralisation des méthodes communes aux collecteurs.
 * Utilisée par {@link TechnicienPetrolier} et {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public interface Collecteur {
    /**
     * Permet de collecter la ressource qui peut se trouver dans la même case de l'agent collecteur.
     *
     * @return le volume collecté
     * @throws Exception si la collecte n'est pas possible
     */
    int collecter() throws Exception;

    /**
     * Renvoie la capacité de stockage par l'agent.
     *
     * @return la capacité de stockage
     */
    int getCapaciteDeStockage();

    /**
     * Renvoie la quantité collectée par l'agent.
     *
     * @return la quantité collectée
     */
    int getQuantiteCollectee();

    /**
     * Renvoie si le stockage de l'agent est plein ou pas.
     *
     * @return si la quantité collectée est supérieure ou égale à la capacité de stockage
     */
    boolean estPlein();

    /**
     * Renvoie la quantité collectée par l'agent et la réinitialise.
     *
     * @return la quantité collectée
     */
    int videCollecte();
}
