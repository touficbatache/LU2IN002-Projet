/**
 * Généralisation des méthodes communes aux collecteurs.
 * Utilisée par {@link TechnicienPetrolier} et {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public abstract class Collecteur extends Agent {
    protected final int capaciteDeCollecte;
    protected final int capaciteDeStockage;
    private int qteCollectee;

    /**
     * Constructeur qui initialise l'agent collecteur avec un type et un terrain
     *
     * @param type type d'Agent
     * @param t    terrain sur lequel se trouve l'Agent
     */
    public Collecteur(String type, Terrain t, int capaciteDeCollecte, int capaciteDeStockage) {
        super(type, t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeStockage = capaciteDeStockage;
        this.qteCollectee = 0;
    }

    /**
     * Permet de tester si la collecte est possible ou pas.
     *
     * @return si la collecte est possible
     * @throws Exception si la collecte n'est pas possible pour une raison fatale
     */
    public abstract boolean verifierCollectePossible() throws Exception;

    /**
     * Permet de collecter la ressource qui peut se trouver dans la même case de l'agent collecteur
     *
     * @param ressource ressource à collecter
     * @return le volume de la collecte
     */
    public int collecter() throws Exception {
        if (!verifierCollectePossible()) {
            return -1;
        }

        Ressource ressource = getTerrain().getCase(getPosX(), getPosY());
        int aCollecter = Math.min(Math.min(ressource.getQuantite(), capaciteDeCollecte), capaciteDeStockage - getQuantiteCollectee());
        ressource.setQuantite(ressource.getQuantite() - aCollecter);
        if (ressource.getQuantite() == 0) {
            getTerrain().videCase(ressource.getX(), ressource.getY());
        }
        qteCollectee += aCollecter;
        return aCollecter;
    }

    /**
     * Renvoie la quantité collectée par l'agent.
     *
     * @return la quantité collectée
     */
    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    /**
     * Renvoie si le stockage de l'agent est plein ou pas.
     *
     * @return si la quantité collectée est supérieure ou égale à la capacité de stockage
     */
    public boolean estPlein() {
        return qteCollectee >= capaciteDeStockage;
    }

    /**
     * Renvoie la quantité collectée par l'agent et la réinitialise.
     *
     * @return la quantité collectée
     */
    public int videCollecte() {
        int qte = qteCollectee;
        qteCollectee = 0;
        return qte;
    }

    /**
     * Renvoie les infos relatives au collecteur.
     *
     * @return les infos relatives au collecteur.
     */
    @Override
    public String toString() {
        return super.toString() +
                " Je peux stocker " + capaciteDeStockage + "kg avec moi." +
                " À chaque collecte, je peux ramasser " + capaciteDeCollecte + "kg" +
                " et j'ai déjà " + getQuantiteCollectee() + "kg sur moi.";
    }
}
