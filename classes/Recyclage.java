import java.util.ArrayList;

public class Recyclage {
    private int quantRecycle=0;
    private int quantMaxRecyclable;
    private ArrayList<PlastiquePolluant> listeRecycles;

    public Recyclage(int max){
        listeRecycles=new ArrayList<PlastiquePolluant>();
        quantMaxRecyclable=max;
    }

    public void ajouterRecyclage(PlastiquePolluant p,Terrain t){
        if(quantRecycle<quantMaxRecyclable){
            if(p instanceof PlastiquePolluant){
                p.recycler(t);
                listeRecycles.add((PlastiquePolluant)p);
            }
        }
        quantRecycle++;
    }

    public void afficheListe(){
        for(PlastiquePolluant p : listeRecycles){
            System.out.println(p.toString()+ "\t");
        }
    }

    public int getQuantRecycle(){
        return quantRecycle;
    }

    public String toString(){
        return getQuantRecycle()+" plastqiues ont été recyclés !";
    }
}


