import java.util.ArrayList;

public class PlastiquePolluant extends Plastique {

    private int quantRecycle=0;
    private boolean estRecycle=false;
    private int quantMaxRecyclable;

    public PlastiquePolluant(int quant,int max){
        super("PP",quant); quantMaxRecyclable=max;
    }
    
    public ArrayList<PlastiquePolluant> recyclage(Terrain t){
        //parcours le terrain si le plastique est polluant on le retire(on ajoute a la liste des plastique retirés), et on le recycle.
        //Renvoie la liste des plastiques recycles
        //On stocke la quantite du plastiques recycles

        ArrayList<PlastiquePolluant> res= new ArrayList<PlastiquePolluant>();

        if(quantRecycle<quantMaxRecyclable){ //Exception instead of  if ?
            for(int i=0;i<t.nbLignes;i++){
                for(int j=0;j<t.nbColonnes;j++)
                if( t.getCase(i, j) instanceof PlastiquePolluant){
                    res.add((PlastiquePolluant)t.getCase(i, j));
                    t.videCase(i, j);
                }
            }
        }
        quantRecycle+=res.size();
        estRecycle=true;
        return res;
    }


    public int getQuantRecycle(){
        return quantRecycle;
    }

    
    @Override
    public String toString(){
        String s=super.toString()+" (Plastique Polluant)";
        if(estRecycle){
            s+="qui ont été recycles";
        }else{
            s+="non recycles";
        }
        return s;
    }
}
