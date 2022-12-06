import java.util.ArrayList;

public class PlastiquePolluant extends Plastique {

    private boolean estRecycle = false;

    public PlastiquePolluant(int qte) {
        super("PP", qte);
    }

    public void recyclage() {
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
