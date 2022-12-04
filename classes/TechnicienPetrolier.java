public class TechnicienPetrolier extends Humain {
    private int quantiteDeCollecte;
    private int capaciteMaxDeCollecte;
    private int qteCollectee;

    public TechnicienPetrolier(int quantiteDeCollecte, int capaciteMaxDeCollecte, Terrain t, int x, int y) {
        super("Technicien Petrolier", t, x, y);

        this.quantiteDeCollecte = quantiteDeCollecte;
        this.capaciteMaxDeCollecte = capaciteMaxDeCollecte;
        this.qteCollectee = 0;
    }

    public boolean collecte() {
        Ressource ressourceACollecter = getTerrain().getCase(getPosX(), getPosY());
        
        if (ressourceACollecter == null) {
            System.out.println("Pas de ressource sur cette case.");
            return false;
        }

        if (!(ressourceACollecter instanceof Petrole)) {
            System.out.println("La ressource n'est pas du pétrole.");
            return false;
        }

        int aCollecter = Math.min(ressourceACollecter.getQuantite(), quantiteDeCollecte);
        if (qteCollectee + aCollecter > capaciteMaxDeCollecte) {
            System.out.println("Je ne peux pas stocker plus de pétrole avec moi.");
            return false;
        }
        ressourceACollecter.setQuantite(ressourceACollecter.getQuantite() - aCollecter);
        qteCollectee += aCollecter;
        return true;
    }

    public int getQuantiteCollectee() {
        return qteCollectee;
    }

    public int videCollecte() {
        int qte = qteCollectee;
        qteCollectee = 0;
        return qte;
    }

    @Override
    public String toString() {
        return super.toString()
        + " Je peux collecter " + quantiteDeCollecte + "T de pétrole en une seule collecte"
        + ", je peux garder " + capaciteMaxDeCollecte + "T au maximum sur moi"
        + " et j'en ai déjà " + qteCollectee + "T.";
    }
}
