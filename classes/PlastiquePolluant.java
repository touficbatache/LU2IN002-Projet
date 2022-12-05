public class PlastiquePolluant extends Plastique {
   
    private boolean estRecycle=false;
    
    public PlastiquePolluant(){
        super("PP"); 
    }
    
    public void recycler(Terrain t){ 
        if(t.getCase(super.getX(), super.getY()) instanceof PlastiquePolluant){
            t.videCase(super.getX(), super.getY());
        }
        estRecycle=true;
    }

    @Override
    public String toString(){
        String s=super.toString();
        if(estRecycle){
            s+=" Le plastique est recycle";
        }else{
            s+=" Le plastique n'est pas recycle";
        }
        return s;
    }
}
