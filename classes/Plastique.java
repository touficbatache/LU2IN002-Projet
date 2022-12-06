/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 * Classe mère des plastiques polluant et bio-dégradable.
 * Classes filles sont {@PlastiquePolluant} et {@PlastiqueBioDegradable}.
 * 
 */

public abstract class Plastique extends Ressource {
    private int age;

    public Plastique(String type, int qte) {
        super(type, qte);
        age = 0; // a l'instant du depot ou de crwation
    }

    public int getAge() {
        return age;
    }

    public void augmenteAge() {
        age++;
    }
}
