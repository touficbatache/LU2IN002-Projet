public class TravailleurUsine extends Agent implements Collecteur {
    private int capaciteDeCollecte;
    private int capaciteDeStockage;
    private int qteCollectee;

    public TravailleurUsine(int capaciteDeCollecte, int capaciteDeStockage, Terrain t) {
        super("TravailleurUsine", t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeStockage = capaciteDeStockage;
        this.qteCollectee = 0;
    }

    @Override
    public StatutReponse collecter() {
        if (estPlein()) {
            return new StatutReponse(false, "Je ne peux pas stocker plus de plastique avec moi. Besoin de déposer à l'usine.");
        }

        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());

        if (ressourceACollecter == null) {
            return new StatutReponse(false, "Pas de ressource sur cette case.");
        }

        if (!(ressourceACollecter instanceof PlastiquePolluant)) {
            return new StatutReponse(false, "La ressource n'est pas du plastique polluant.");
        }

        int aCollecter = Math.min(Math.min(ressourceACollecter.getQuantite(), capaciteDeCollecte), getCapaciteDeStockage() - getQuantiteCollectee());
        // if (getQuantiteCollectee() + aCollecter > getCapaciteDeStockage()) {
        //     return new StatutReponse(false, "Je ne peux pas stocker plus de plastique avec moi. Besoin de déposer à l'usine.");
        // }
        ressourceACollecter.setQuantite(ressourceACollecter.getQuantite() - aCollecter);
        if (ressourceACollecter.getQuantite() == 0) {
            getTerrain().videCase(ressourceACollecter.getX(), ressourceACollecter.getY());
        }
        qteCollectee += aCollecter;
        return new StatutReponse(true, "J'ai collecté " + aCollecter + "kg de plastique. J'ai " + getQuantiteCollectee() + "kg en tout.");
    }

    @Override
    public int getCapaciteDeStockage() {
        return capaciteDeStockage;
    }

    @Override
    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    @Override
    public boolean estPlein() {
        return getQuantiteCollectee() >= capaciteDeStockage;
    }

    @Override
    public int videCollecte() {
        int qte = getQuantiteCollectee();
        qteCollectee = 0;
        return qte;
    }

    @Override
    public String toString() {
        return super.toString() +
        " Je peux stocker " + getCapaciteDeStockage() + "kg de plastique avec moi." +
        " À chaque collecte, je peux ramasser " + capaciteDeCollecte + "kg de plastique polluant" +
        " et j'en ai déjà " + getQuantiteCollectee() + "kg sur moi.";
    }
}
