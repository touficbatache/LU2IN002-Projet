public class PlastiquePolluant extends Plastique {

    private boolean estRecycle = false;

    public PlastiquePolluant(int qte) {
        super("PP", qte);
    }

    public void recycler(Terrain t) {
        if (t.getCase(super.getX(), super.getY()) instanceof PlastiquePolluant) {
            t.videCase(super.getX(), super.getY());
        }
        estRecycle = true;
    }

    @Override
    public String toString() {
        String s = super.toString();
        if (estRecycle) {
            s += " Le plastique est recyclé";
        } else {
            s += " Le plastique n'est pas recyclé";
        }
        return s;
    }
}
