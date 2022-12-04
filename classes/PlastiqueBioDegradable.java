public class PlastiqueBioDegradable extends Plastique implements Ecologique {

    private int temps;
    private boolean decompose=false;

    public PlastiqueBioDegradable(int quant){
        super("PBD",quant);
        temps=0; //a l'instant du depot 
        }

    public void decomposition(Terrain t){
        for(int i=0;i<t.nbLignes;i++){
            for(int j=0;j<t.nbColonnes;j++)
            if( t.getCase(i, j) instanceof PlastiqueBioDegradable){
                if(temps>=1){
                    t.videCase(i, j);
                }
            }
        }
        decompose=true;
    }

    public void augmenteTemps(){
        temps++;
    }

    @Override
    public String toString(){
        String s=super.toString()+" Plastique Biodegradable";
        if(decompose){
            s+=" decomposes";
        }else{
            s+=".Besoin plus de temps pour la decomposition";
        }
        return s;
    }


    
}
