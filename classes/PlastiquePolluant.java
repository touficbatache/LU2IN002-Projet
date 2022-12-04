public class PlastiquePolluant extends Plastique {
   
    private boolean estRecycle=false;
    
    public PlastiquePolluant(int quant,int max){
        super("PP",quant); 
    }
    
    public void recycler(Terrain t){ //parcours le terrain si le plastique est polluant on le retire(et on le recycle).
        if( t.getCase(super.getX(), super.getY()) instanceof PlastiquePolluant){
            t.videCase(super.getX(), super.getY());
        }
        estRecycle=true;
    }


    @Override
    public String toString(){
        String s=super.toString();
        if(estRecycle){
            s+="Le plastique est recycle";
        }else{
            s+="Le plastique n'est pas recycle";
        }
        return s;
    }
}
