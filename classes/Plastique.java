public abstract class Plastique extends Ressource {

    public Plastique(String type,int quant){
        super(type,quant);
    }

    @Override
    public String toString(){
        return "On trouve "+super.getQuantite()+" "+this.type;
    }
}
