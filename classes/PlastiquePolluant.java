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

    public PlastiquePolluant(int qte, int ageLimiteDeRecyclage) {
        super("PP", qte);

        this.ageLimiteDeRecyclage = ageLimiteDeRecyclage;
    }

    public boolean estRecyclagePossible() {
        return getAge() < ageLimiteDeRecyclage;
    }

    @Override
    public String toString() {
        return super.toString() +
                ((estRecyclagePossible()) ? " S'est recyclé !" : " Ne peux plus être recyclé");
    }
}
