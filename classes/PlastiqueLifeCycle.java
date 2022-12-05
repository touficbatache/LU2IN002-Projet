import java.util.ArrayList;

public class PlastiqueLifeCycle {
    private int qteRecycle = 0;
    private int qteMaxRecyclable;
    private int qtetDecomp=0;
    private ArrayList<Plastique> liste_P;
    

    public PlastiqueLifeCycle(int max) {
        liste_P = new ArrayList<Plastique>();
        qteMaxRecyclable=max;
    }


    public ArrayList<Plastique> ajouterLifeCycle(Terrain t){
        for(int i=0;i<t.nbLignes;i++){
            for(int j=0;j<t.nbColonnes;j++){
                if(t.getCase(i, j) instanceof Plastique){
                    liste_P.add((Plastique)t.getCase(i, j));
                }
            }
        }
        return liste_P;
    }

    public int qteP(){
        return liste_P.size();
    }

    public ArrayList<PlastiquePolluant> allRecyclage(Terrain t) {
        ArrayList<PlastiquePolluant> res =new ArrayList<PlastiquePolluant>();
        if (qteRecycle < qteMaxRecyclable) {
            for(Plastique p : this.liste_P){
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

    public int getQteRecycle(){
        return qteRecycle;
    }

    public ArrayList<PlastiqueBioDegradable> allDecomposition(Terrain t) {
        ArrayList<PlastiqueBioDegradable> res =new ArrayList<PlastiqueBioDegradable>();
        for(Plastique p : this.liste_P){
            if (p instanceof PlastiqueBioDegradable) {
                ((PlastiqueBioDegradable) p).decomposition(t);
                System.out.println(p.toString());
                qtetDecomp++;
                res.add((PlastiqueBioDegradable) p);
            }
        }
        return res;  
    }

    public int getQteDecomp(){
        return qtetDecomp;
    }

    public void allAugmenteAge(){
        for(Plastique p : this.liste_P){
            p.augmenteAge();
        }
    }

    public void afficheListe() {
        for (Plastique p : liste_P) {
            System.out.println(p.toString() + "\t");
        }
    }


    public String toString() {
        return "On trouve "+qteP()+" plastiques dans le terrain dont "+getQteDecomp()+" se sont decomposés et "+getQteRecycle()+"ont été recyclés ! ";
    }
}
