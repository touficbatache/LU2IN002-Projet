/**
 *
 * Classe qui contient le statut de retour de la fonction ainsi qu'un message.
 * Ce dernier peut être un message d'erreur, comme il peut être un message de
 * succès. Le booléen {@code succes} déterminera s'il sagit de l'un ou l'autre.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 */

public class StatutReponse {
    public boolean succes;
    public String message;

    public StatutReponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }
}
