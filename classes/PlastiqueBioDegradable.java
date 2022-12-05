public class PlastiqueBioDegradable extends Plastique {

    private boolean decompose = false;

    public PlastiqueBioDegradable(int qte) {
        super("PBD", qte);
    }

    public void decomposition(Terrain t) {
        if (t.getCase(super.getX(), super.getY()) instanceof PlastiqueBioDegradable) {
            if (this.getAge()>= 1) {
                t.videCase(super.getX(), super.getY());
            }
        }
        decompose = true;
    }

    
    @Override
    public String toString() {
        String s = super.toString();
        if (decompose) {
            s += " S'est décomposé !";
        } else {
            s += " Besoin plus de temps pour la décomposition";
        }
        return s;
    }
}
