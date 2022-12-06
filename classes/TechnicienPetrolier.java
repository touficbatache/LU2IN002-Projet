/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 * Représentation d'un Technicien Pétrolier, agent qui parcourt le terrain
 * et collecte du pétrole dans des barils lorsqu'il en trouve. Il peut aussi
 * transformer ses réserves en plastique.
 *
 */

public class TechnicienPetrolier extends Agent implements Collecteur {
    private int capaciteDeCollecte;
    private int capaciteDeBarril;
    private int nbBarrils;
    private int qteCollectee;

    public TechnicienPetrolier(int capaciteDeCollecte, int capaciteDeBarril, int nbBarrils, Terrain t) {
        super("TechnicienPetrolier", t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeBarril = capaciteDeBarril;
        this.nbBarrils = nbBarrils;
        this.qteCollectee = 0;
    }

    @Override
    public StatutReponse collecter() {
        if (estPlein()) {
            return new StatutReponse(false, "Je ne peux pas stocker plus de pétrole avec moi.");
        }

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());
        
        if (ressourceACollecter == null) {
            return new StatutReponse(false, "Pas de ressource sur cette case.");
        }

        if (!(ressourceACollecter instanceof Petrole)) {
            return new StatutReponse(false, "La ressource n'est pas du pétrole.");
        }

        int aCollecter = Math.min(Math.min(ressourceACollecter.getQuantite(), capaciteDeCollecte), getCapaciteDeStockage() - getQuantiteCollectee());
        if (getQuantiteCollectee() + aCollecter > getCapaciteDeStockage()) {
            return new StatutReponse(false, "Je ne peux pas stocker plus de pétrole avec moi.");
        }
        ressourceACollecter.setQuantite(ressourceACollecter.getQuantite() - aCollecter);
        if (ressourceACollecter.getQuantite() == 0) {
            getTerrain().videCase(ressourceACollecter.getX(), ressourceACollecter.getY());
        }
        qteCollectee += aCollecter;
        return new StatutReponse(true, "J'ai collecté " + aCollecter + "L de pétrole. Mes barils contiennent " + getQuantiteCollectee() + "L en tout.");
    }

    @Override
    public int getCapaciteDeStockage() {
        return capaciteDeBarril * nbBarrils;
    }

    @Override
    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    @Override
    public boolean estPlein() {
        return getQuantiteCollectee() >= getCapaciteDeStockage();
    }

    @Override
    public int videCollecte() {
        int qte = getQuantiteCollectee();
        qteCollectee = 0;
        return qte;
    }

    @Override
    public String toString() {
        return super.toString()
        + " J'ai " + nbBarrils + " barrils qui, réunis, peuvent contenir " + getCapaciteDeStockage() + "L de pétrole."
        + " À chaque collecte, je peux extraire " + capaciteDeCollecte + "L seulement et j'en ai déjà " + getQuantiteCollectee() + "L.";
    }
}
