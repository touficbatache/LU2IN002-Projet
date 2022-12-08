/**
 * Exception qui indique qu'il n'y a pas eu de collecte avant
 * l'appel à une méthode qui utilise les collectes.
 */

public class PasDeCollecteException extends Exception {
    /**
     * @param methodeActuelle la méthode dans laquelle l'erreur s'est produite
     * @param methodeAAppeler la méthode à appeler avant la méthode actuelle pour éviter l'erreur
     */
    public PasDeCollecteException(String methodeActuelle, String methodeAAppeler) {
        super("Pas de collecte ! Appelez la méthode " + methodeAAppeler + "() avant d'appeler " + methodeActuelle + "().");
    }
}
