/**
 * Plastique biodégradable qui se décompose dans l'eau après
 * un bout de temps, contrairement au {@link PlastiquePolluant}
 * qui a besoin d'être ramassé par un {@link TravailleurUsine}.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class PlastiqueBioDegradable extends Plastique {
    private int dureeDeVie;

    /**
     * Constructeur qui initialise la quantité du plastique biodégradable ainsi que sa durée de vie
     *
     * @param qte        quantité du plastique biodégradable
     * @param dureeDeVie durée de vie, l'âge auquel se décompose le plastique
     */
    public PlastiqueBioDegradable(int qte, int dureeDeVie) {
        super("PBD", qte);

        this.dureeDeVie = dureeDeVie;
    }

    /**
     * Constructeur qui initialise la quantité du plastique biodégradable ainsi que sa durée de vie
     *
     * @param pp un plastique polluant
     */
    public PlastiqueBioDegradable(PlastiquePolluant pp) {
        super("PBD", pp.getQuantite());

        this.dureeDeVie = Simulation.randEntre(1, 2);
    }

    /**
     * Vérifie si assez de temps est passé pour que le plastique biodégradable se décompose
     *
     * @return booléen qui indique si la décomposition est possible ou pas
     */
    public boolean estDecompositionPossible() {
        return getAge() >= dureeDeVie;
    }

    /**
     * Renvoie des informations sur le plastique biodégradable
     *
     * @return l'ID et la quantité du plastique biodégradable, s'il est présent
     * sur le terrain et ses coordonnées si oui, et si le plastique s'est décomposé ou non
     */
    @Override
    public String toString() {
        return super.toString() +
                ", dureeDeVie: " + dureeDeVie + "." +
                ((estDecompositionPossible()) ? " S'est décomposé !" : " Besoin plus de temps pour la décomposition...");
    }
}
