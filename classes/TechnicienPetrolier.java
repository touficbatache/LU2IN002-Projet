/**
 * Représentation d'un Technicien Pétrolier, agent qui parcourt le terrain
 * et collecte du pétrole dans des barils lorsqu'il en trouve. Il peut aussi
 * transformer ses réserves en plastique.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class TechnicienPetrolier extends Agent implements Collecteur {
    private final int capaciteDeCollecte;
    private final int capaciteDeBaril;
    private final int nbBarils;
    private int qteCollectee;

    /**
     * Constructeur qui initialise les différentes capacités du TechnicienPetrolier ainsi le terrain sur lequel il se trouve et le nombre de barils
     *
     * @param capaciteDeCollecte capacité de pétrole que peut collecter le TechnicienPetrolier
     * @param capaciteDeBaril    la capacité des barils
     * @param nbBarils           le nombre de barils
     * @param t                  Terrain sur lequel se trouve le TechnicienPetrolier
     */
    public TechnicienPetrolier(int capaciteDeCollecte, int capaciteDeBaril, int nbBarils, Terrain t) {
        super("TechnicienPetrolier", t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeBaril = capaciteDeBaril;
        this.nbBarils = nbBarils;
        this.qteCollectee = 0;
    }

    /**
     * Permet de collecter le Pétrole qui peut se trouver dans la même case du TechnicienPetrolier,
     * si la capacité de collecte et la capacité de stockage le permettent.
     *
     * @throws Exception s'il n'y a plus de place pour stocker le pétrole
     */
    @Override
    public int collecter() throws Exception {
        if (estPlein()) throw new Exception("Je ne peux pas stocker plus de pétrole avec moi.");

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());

        // Pas de ressource sur cette case ou la ressource n'est pas du pétrole.
        if (!(ressourceACollecter instanceof Petrole)) return -1;

        int aCollecter = Math.min(Math.min(ressourceACollecter.getQuantite(), capaciteDeCollecte), getCapaciteDeStockage() - qteCollectee);
        ressourceACollecter.setQuantite(ressourceACollecter.getQuantite() - aCollecter);
        if (ressourceACollecter.getQuantite() == 0) {
            getTerrain().videCase(ressourceACollecter.getX(), ressourceACollecter.getY());
        }
        qteCollectee += aCollecter;
        return aCollecter;
    }

    /**
     * Renvoie la capacité de stockage de pétrole du technicien pétrolier.
     *
     * @return la capacité de stockage de pétrole
     */
    @Override
    public int getCapaciteDeStockage() {
        return capaciteDeBaril * nbBarils;
    }

    /**
     * Renvoie la quantité de pétrole collectée par le technicien pétrolier.
     *
     * @return la quantité de pétrole collectée
     */
    @Override
    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    /**
     * Renvoie si le stockage est plein ou pas.
     *
     * @return si le stockage est plein, ne peut plus stocker plus de pétrole
     */
    @Override
    public boolean estPlein() {
        return qteCollectee >= getCapaciteDeStockage();
    }

    /**
     * Renvoie la quantité extraite et la réinitialise.
     *
     * @return la quantité de pétrole extraite
     */
    @Override
    public int videCollecte() {
        int qte = qteCollectee;
        qteCollectee = 0;
        return qte;
    }

    /**
     * Renvoie des informations sur le TechnicienPetrolier
     *
     * @return l'ID et la position du TechnicienPetrolier, le nombre de barils et leur capacité de stockage,
     * la capacité de collecte et la quantité collectée
     */
    @Override
    public String toString() {
        return super.toString()
                + " J'ai " + nbBarils + " barils qui, réunis, peuvent contenir " + getCapaciteDeStockage() + "L de pétrole."
                + " À chaque collecte, je peux extraire " + capaciteDeCollecte + "L seulement et j'en ai déjà " + qteCollectee + "L.";
    }
}
