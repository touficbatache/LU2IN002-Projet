public class PlastiquePolluant extends Plastique {

    private boolean estRecycle = false;

    public PlastiquePolluant(int qte) {
        super("PP", qte);
    }

    public PlastiqueBioDegradable recyclage() {
        estRecycle = true;
        return new PlastiqueBioDegradable(getQuantite());
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
