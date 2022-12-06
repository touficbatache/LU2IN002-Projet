import java.util.ArrayList;

public class TravailleurUsine extends Agent implements Collecteur {
    private int capaciteDeCollecte;
    private int capaciteDeStockage;
    private ArrayList<PlastiquePolluant> listeCollectes = new ArrayList<PlastiquePolluant>();

    public TravailleurUsine(int capaciteDeCollecte, int capaciteDeStockage, Terrain t) {
        super("TravailleurUsine", t);

        this.capaciteDeCollecte = capaciteDeCollecte;
        this.capaciteDeStockage = capaciteDeStockage;
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
        listeCollectes.add(new PlastiquePolluant(aCollecter));
        return new StatutReponse(true, "J'ai collecté " + aCollecter + "kg de plastique. J'ai " + getQuantiteCollectee() + "kg en tout.");
    }

    public void collecterPlastique() {
        if (getQuantiteCollectee() < capaciteDeCollecte) {
            for (int i = 0; i < getTerrain().nbLignes; i++) {
                for (int j = 0; j < getTerrain().nbColonnes; j++) {
                    if (getTerrain().getCase(i, j) instanceof PlastiquePolluant) {
                        seDeplacer(i, j);
                        listeCollectes.add((PlastiquePolluant) getTerrain().getCase(i, j));
                        getTerrain().videCase(i, j);
                    }
                }
            }
            System.out.println("Tout le plastique présent sur le terrain a été collecté !");
        } else {
            System.out.println("Il faut déposer le plastique a l'usine");
        }
    }

    @Override
    public int getCapaciteDeStockage() {
        return capaciteDeStockage;
    }

    @Override
    public int getQuantiteCollectee() {
        return listeCollectes.size();
    }

    @Override
    public boolean estPlein() {
        return getQuantiteCollectee() >= capaciteDeStockage;
    }

    public ArrayList<PlastiquePolluant> videCollecte() {
        ArrayList<PlastiquePolluant> liste = new ArrayList<PlastiquePolluant>();
        liste.addAll(listeCollectes);
        listeCollectes.clear();
        return liste;
    }

    @Override
    public String toString() {
        return super.toString() +
        " Je peux stocker " + getCapaciteDeStockage() + "kg de plastique avec moi." +
        " À chaque collecte, je peux ramasser " + capaciteDeCollecte + "kg de plastique polluant" +
        " et j'en ai déjà " + getQuantiteCollectee() + "kg sur moi.";
    }
}
