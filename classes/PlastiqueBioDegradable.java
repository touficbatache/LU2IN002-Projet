/**
 *
 * Plastique biodégradable qui se décompose dans l'eau après
 * un bout de temps, contrairement au {@link PlastiquePolluant}
 * qui a besoin d'être ramassé par un {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 */

public class PlastiqueBioDegradable extends Plastique {
    private boolean decompose = false;

    /**
     * Constructeur qui initialise la quantité du plastique biodégradable
     * @param qte quantité du plastique biodégradable
     */
    public PlastiqueBioDegradable(int qte) {
        super("PBD", qte);
    }

    /**
     * Vérifie si assez de temps est passé pour que le plastique biodégradable se décompose et réalise la décomposition
     * @param t Terrain sur lequel se trouve le plastique
     */
    public void decomposition(Terrain t) {
        if (t.getCase(super.getX(), super.getY()) instanceof PlastiqueBioDegradable) {
            if (this.getAge() >= 1) {
                t.videCase(super.getX(), super.getY());
            }
        }
        decompose = true;
    }

    /**
     * Renvoie des informations sur le plastique biodégradable
     * @return l'ID et la quantité du plastique biodégradable, s'il est présent sur le terrain et ses coordonnées si oui, et si le plastique s'est décomposé ou non
     */
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
