/**
 *
 * Plastique polluant qui ne se décompose pas dans l'eau
 * tout seul. Il est ramassé par les {@link TravailleurUsine}
 * pour être recyclé en {@link PlastiqueBioDegradable}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 */

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
