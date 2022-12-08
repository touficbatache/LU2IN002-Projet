/**
 * Représentation d'un Travailleur à l'usine, agent qui parcourt le terrain,
 * ramasse et stocke du plastique polluant lorsqu'il en trouve. Il peut aussi
 * transformer ses réserves en plastique bio-dégradable.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class TravailleurUsine extends Collecteur {
    /**
     * Constructeur qui initialise la capacité de collecte et de stockage du TravailleurUsine
     *
     * @param capaciteDeCollecte capacité de collecte du TravailleurUsine
     * @param capaciteDeStockage capacité de stockage du TravailleurUsine
     * @param t                  terrain sur lequel se trouve le TravailleurUsine
     */
    public TravailleurUsine(int capaciteDeCollecte, int capaciteDeStockage, Terrain t) {
        super("TravailleurUsine", t, capaciteDeCollecte, capaciteDeStockage);
    }

    /**
     * Permet de collecter le PlastiquePolluant qui peut se trouver dans la même case du TravailleurUsine,
     * si la capacité de collecte et la capacité de stockage le permettent.
     *
     * @throws Exception s'il n'y a plus de place pour stocker le plastique
     */
    @Override
    public boolean verifierCollectePossible() throws Exception {
        if (estPlein())
            throw new Exception("Je ne peux pas stocker plus de plastique avec moi. Besoin de déposer à l'usine.");

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());

        // S'il n'y a pas de ressource sur cette case ou la ressource n'est pas du pétrole, ou
        // si le recyclage n'est pas possible, on retourne false.
        return ressourceACollecter instanceof PlastiquePolluant &&
                ((PlastiquePolluant) ressourceACollecter).estRecyclagePossible();
    }

    /**
     * Renvoie des informations sur le TravailleurUsine.
     *
     * @return l'ID et la position du TravailleurUsine, sa capacité de stockage,
     * sa capacité de collecte et la quantité collectée
     */
    @Override
    public String toString() {
        return super.toString() +
                " Je peux stocker " + capaciteDeStockage + "kg de plastique avec moi." +
                " À chaque collecte, je peux ramasser " + capaciteDeCollecte + "kg de plastique polluant" +
                " et j'en ai déjà " + getQuantiteCollectee() + "kg sur moi.";
    }
}
