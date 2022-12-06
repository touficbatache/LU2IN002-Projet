import java.util.ArrayList;

public class TravailleurUsine extends Agent {

    private int qteMaxCollecter;
    private int qteCollecter = 0;
    private ArrayList<PlastiquePolluant> liste_Collecter = new ArrayList<PlastiquePolluant>();

    public TravailleurUsine(Terrain t, int max) {
        super("TravailleurUsine", t);
        qteMaxCollecter = max;
    }

    public void collecterPlastique() {
        if (qteCollecter < qteMaxCollecter) {
            for (int i = 0; i < getTerrain().nbLignes; i++) {
                for (int j = 0; j < getTerrain().nbColonnes; j++) {
                    if (getTerrain().getCase(i, j) instanceof PlastiquePolluant) {
                        seDeplacer(i, j);
                        liste_Collecter.add((PlastiquePolluant) getTerrain().getCase(i, j));
                        qteCollecter++;
                        getTerrain().videCase(i, j);
                    }
                }
            }
            System.out.println("Tout le plastique présent sur le terrain a été collecté !");
        } else {
            System.out.println("Il faut déposer le plastique a l'usine");
        }
    }

    public void resetQteCollecter() {
        qteCollecter = 0;
    }

    public int getQteCollecter() {
        return qteCollecter;
    }

    public ArrayList<PlastiquePolluant> getListeCollecter() {
        return liste_Collecter;
    }

    public String toString() {
        return "J'ai collecté " + getQteCollecter() + " plastiques du terrain pour le recyclage !";
    }
}
