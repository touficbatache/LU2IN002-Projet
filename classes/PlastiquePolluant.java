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

    
    /**
     * Constructeur qui initialise la quantité du plastique polluant
     * @param qte quantité du plastique polluant
     */
    public PlastiquePolluant(int qte) {
        super("PP", qte);
    }

    /**
     * Recylce le plastique polluant en le transformant en plastique biodégradable
     * @return le nouveau plastique biodégradable
     */
    public PlastiqueBioDegradable recyclage() {
        estRecycle = true;
        return new PlastiqueBioDegradable(getQuantite());
    }

    /**
     * Renvoie des informations sur le plastique polluant
     * @return l'ID, la quantité du plastique polluant, et s'il a été recyclé
     */
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
