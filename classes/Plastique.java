/**
 * Classe mère des plastiques polluant et bio-dégradable.
 * Classes filles sont {@link PlastiquePolluant} et {@link PlastiqueBioDegradable}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public abstract class Plastique extends Ressource {
    private int age;

    /**
     * Constructeur qui initialise le type du plastique et sa quantité. Au moment de création, l'âge est 0 par défaut.
     *
     * @param type type du plastique
     * @param qte  quantité du plastique
     */
    public Plastique(String type, int qte) {
        super(type, qte);
        age = 1; // à l'instant du dépôt ou de création
    }

    /**
     * Permet d'avoir accès à l'âge du plastique.
     *
     * @return l'âge du plastique
     */
    public int getAge() {
        return age;
    }

    /**
     * Augmente l'âge du plastique d'un an
     */
    public void augmenteAge() {
        age++;
    }

    /**
     * Renvoie les infos relatives au plastique, notamment l'âge.
     *
     * @return les infos relatives au plastique, notamment l'âge.
     */
    @Override
    public String toString() {
        return super.toString() + " age: " + getAge();
    }
}
