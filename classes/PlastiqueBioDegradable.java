public class PlastiqueBioDegradable extends Plastique implements Ecologique {

    private int temps;
    private boolean decompose=false;

    public PlastiqueBioDegradable(int quant){
        super("PBD",quant);
        temps=0; //a l'instant du depot 
        }

    public void decomposition(Terrain t){
        if( t.getCase(super.getX(), super.getY()) instanceof PlastiqueBioDegradable){
            if(temps>=1){
                t.videCase(super.getX(), super.getY());
            }
        }
        decompose=true;
    }

    public void augmenteTemps(){
        temps++;
    }

    @Override
    public String toString(){
        String s=super.toString();
        if(decompose){
            s+=" Le Plastique s'est decompose";
        }else{
            s+="Besoin plus de temps pour la decomposition";
        }
        return s;
    }


    
}
