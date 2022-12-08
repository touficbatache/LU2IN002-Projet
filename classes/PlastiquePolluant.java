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
    private int ageLimiteDeRecyclage;


    /**
     * Constructeur qui initialise la quantité du plastique polluant
     * @param qte quantité du plastique polluant
     */
    public PlastiquePolluant(int qte, int ageLimiteDeRecyclage) {
        super("PP", qte);

        this.ageLimiteDeRecyclage = ageLimiteDeRecyclage;
    }

    /**
     * Recylce le plastique polluant en le transformant en plastique biodégradable
     * @return le nouveau plastique biodégradable
     */
    public boolean estRecyclagePossible() {
        return getAge() < ageLimiteDeRecyclage;
    }

    /**
     * Renvoie des informations sur le plastique polluant
     * @return l'ID, la quantité du plastique polluant, et s'il a été recyclé
     */
    @Override
    public String toString() {
        return super.toString() +
                ((estRecyclagePossible()) ? " S'est recyclé !" : " Ne peux plus être recyclé");
    }
}
