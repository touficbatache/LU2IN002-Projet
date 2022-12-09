/**
 * Représentation d'un Technicien Pétrolier, agent qui parcourt le terrain
 * et collecte du pétrole dans des barils lorsqu'il en trouve. Il peut aussi
 * transformer ses réserves en plastique.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class TechnicienPetrolier extends Collecteur {
    private int nbBarils;
    /**
     * Constructeur qui initialise la capacité de collecte et de stockage du TechnicienPetrolier
     *
     * @param capaciteDeCollecte capacité de pétrole que peut collecter le TechnicienPetrolier
     * @param capaciteDeBaril    la capacité des barils
     * @param nbBarils           le nombre de barils
     * @param t                  terrain sur lequel se trouve le TechnicienPetrolier
     */
    public TechnicienPetrolier(int capaciteDeCollecte, int capaciteDeBaril, int nbBarils, Terrain t) {
        super("TechnicienPetrolier", t, capaciteDeCollecte, capaciteDeBaril * nbBarils);

        this.nbBarils = nbBarils;
    }

    /**
     * Permet de collecter le Pétrole qui peut se trouver dans la même case du TechnicienPetrolier,
     * si la capacité de collecte et la capacité de stockage le permettent.
     *
     * @throws Exception s'il n'y a plus de place pour stocker le pétrole
     */
    @Override
    public boolean verifierCollectePossible() throws Exception {
        if (estPlein()) throw new Exception("Je ne peux pas stocker plus de pétrole avec moi.");

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());

        // S'il n'y a pas de ressource sur cette case ou la ressource n'est pas du pétrole,
        // on retourne false.
        return ressourceACollecter instanceof Petrole;
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
                + " J'ai " + nbBarils + " barils qui, réunis, peuvent contenir " + capaciteDeStockage + "L de pétrole."
                + " À chaque collecte, je peux extraire " + capaciteDeCollecte + "L seulement et j'en ai déjà " + getQuantiteCollectee() + "L.";
    }
}
