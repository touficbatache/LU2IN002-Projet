
/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 * Usine qui gère plusieurs transformations :
 * - la création du plastique à partir du pétrole,
 * - le recyclage du plastique polluant en plastique bio-dégradable
 * 
 */

import java.util.ArrayList;

public class Usine {
    private int qtePetrole = 0;
    private int qteRecycle = 0;
    private int qteMaxRecyclable;
    private ArrayList<Plastique> liste_P = new ArrayList<Plastique>();

    public Usine(int qteMaxRecyclable) {
        this.qteMaxRecyclable = qteMaxRecyclable;
    }
    
    /**
     * Depose le pétrole dans l'usine pour qu'il puisse être recyclé par la suite.
     * 
     * @param qte la quantité de pétrole à déposer
     * 
     */
    public void deposerPetrole(int qte) {
        qtePetrole += qte;
    }

    /**
     * Produit du plastique à partir du pétrole déposé auparavant dans l'usine.
     * 
     * @return le plastique polluant produit
     * 
     */
    public PlastiquePolluant produirePlastique() {
        int qte = qtePetrole;
        qtePetrole = 0;
        return new PlastiquePolluant((int) (qte / 100.0));
    }

    public ArrayList<Plastique> ajouterLifeCycle(Terrain t) {
        for (int i = 0; i < t.nbLignes; i++) {
            for (int j = 0; j < t.nbColonnes; j++) {
                if (t.getCase(i, j) instanceof Plastique) {
                    liste_P.add((Plastique) t.getCase(i, j));
                }
            }
        }
        return liste_P;
    }

    public int qteP() {
        return liste_P.size();
    }

    public ArrayList<PlastiquePolluant> allRecyclage(Terrain t) {
        ArrayList<PlastiquePolluant> res = new ArrayList<PlastiquePolluant>();
        if (qteRecycle < qteMaxRecyclable) {
            for (Plastique p : this.liste_P) {
                if (p instanceof PlastiquePolluant) {
                    ((PlastiquePolluant) p).recyclage(t);
                    System.out.println(p.toString());
                    qteRecycle++;
                    res.add((PlastiquePolluant) p);
                }
            }
        }
        return res;
    }

    public int getQteRecycle() {
        return qteRecycle;
    }


    public void allAugmenteAge() {
        for (Plastique p : this.liste_P) {
            p.augmenteAge();
        }
    }

    public void afficheListe() {
        for (Plastique p : liste_P) {
            System.out.println(p.toString() + "\t");
        }
    }

    @Override
    public String toString() {
        return "On trouve " + qteP() + " plastiques dans le terrain dont "+ getQteRecycle() + "ont été recyclés ! ";
    }
}
