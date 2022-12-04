import java.util.ArrayList;

public class PlastiquePolluant extends Plastique {

    private int QuantRecycle=0;
    private boolean estRecycle=false;

    public PlastiquePolluant(int quant){
        super("Polluant",quant);
    }
    
    public ArrayList<PlastiquePolluant> recyclage(Terrain t){
        //parcours le terrain si le plastique est polluant on le retire(on ajoute a la liste des plastique retirés), et on le recycle.
        //Renvoie la liste des plastiques recycles
        //On stocke la quantite du plastiques recycles
        
        ArrayList<PlastiquePolluant> res= new ArrayList<PlastiquePolluant>();
        for(int i=0;i<t.nbLignes;i++){
            for(int j=0;j<t.nbColonnes;j++)
            if( t.getCase(i, j) instanceof PlastiquePolluant){
                res.add((PlastiquePolluant)t.getCase(i, j));
                t.videCase(i, j);
            }
        }
        QuantRecycle+=res.size();
        estRecycle=true;
        return res;
    }

    public int getQuantRecycle(){
        return QuantRecycle;
    }

    
    @Override
    public String toString(){
        String s="";
        s+="On trouve "+super.getQuantite()+" plastique(s) "+this.type+"(s)";
        if(estRecycle){
            s+="qui ont été recycles";
        }else{
            s+="non recycles";
        }
        return s;
    }
}
