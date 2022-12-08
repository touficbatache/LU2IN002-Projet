/**
 *
 * Représentation d'un Travailleur à l'usine, agent qui parcourt le terrain,
 * ramasse et stocke du plastique polluant lorsqu'il en trouve. Il peut aussi
 * transformer ses réserves en plastique bio-dégradable.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 */

public class TravailleurUsine extends Agent implements Collecteur {
    private final int capaciteDeCollecte;
    private final int capaciteDeStockage;
    private int qteCollectee;

    /**
     * Constructeur qui initialise la capacité de collecte et de stockage du TravailleurUsine ainsi que le Terrain sur lequel il se trouve
     * @param capaciteDeCollecte capacité de collecte du TravailleurUsine
     * @param capaciteDeStockage capacité de stockage du TravailleurUsine
     * @param t Terrain sur lequel se trouve le TravailleurUsine
     */
    public TravailleurUsine(int capaciteDeCollecte, int capaciteDeStockage, Terrain t) {
        super("TravailleurUsine", t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeStockage = capaciteDeStockage;
        this.qteCollectee = 0;
    }

    /**
     * Permet de collecter le PlastiquePolluant qui peut se trouver dans la même case du TravailleurUsine,
     * si la capacité de collecte et la capacité de stockage le permettent.
     */
    @Override
    public int collecter() throws Exception {
        if (estPlein()) throw new Exception("Je ne peux pas stocker plus de plastique avec moi. Besoin de déposer à l'usine.");

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());

        if (!(ressourceACollecter instanceof PlastiquePolluant)) return -1;

        if (!((PlastiquePolluant) ressourceACollecter).estRecyclagePossible()) {
            return -1;
        }

        int aCollecter = Math.min(Math.min(ressourceACollecter.getQuantite(), capaciteDeCollecte), getCapaciteDeStockage() - qteCollectee);
        ressourceACollecter.setQuantite(ressourceACollecter.getQuantite() - aCollecter);
        if (ressourceACollecter.getQuantite() == 0) {
            getTerrain().videCase(ressourceACollecter.getX(), ressourceACollecter.getY());
        }
        qteCollectee += aCollecter;
        return aCollecter;
    }

    /**
     * @return la capacité de stockage de pétrole
     */
    @Override
    public int getCapaciteDeStockage() {
        return capaciteDeStockage;
    }

    /**
     * @return la quantité de plastique polluant collecté
     */
    @Override
    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    /**
     * @return si le stockage est plein, ne peut plus stocker plus de pétrole
     */
    @Override
    public boolean estPlein() {
        return qteCollectee >= getCapaciteDeStockage();
    }

    /**
     * Retourne la quantité collectée et la réinitialise
     *
     * @return la quantité de plastique polluant collectée
     */
    @Override
    public int videCollecte() {
        int qte = qteCollectee;
        qteCollectee = 0;
        return qte;
    }

    /**
     * Renvoie des informations sur le TravailleurUsine
     * @return l'ID et la position du TravailleurUsine, sa capacité de stockage, sa capacité de collecte et la quantité collecctée
     */
    @Override
    public String toString() {
        return super.toString() +
        " Je peux stocker " + getCapaciteDeStockage() + "kg de plastique avec moi." +
        " À chaque collecte, je peux ramasser " + capaciteDeCollecte + "kg de plastique polluant" +
        " et j'en ai déjà " + qteCollectee + "kg sur moi.";
    }
}
