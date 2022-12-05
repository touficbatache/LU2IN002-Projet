/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 * Usine qui gère la création du plastique à partir du pétrole.
 * 
 */

public class Usine {
    private int qtePetrole;

    public Usine() {
        qtePetrole = 0;
    }

    public void deposerPetrole(int qte) {
        qtePetrole += qte;
    }

    public PlastiquePolluant produirePlastique() {
        int qte = qtePetrole;
        qtePetrole = 0;
        return new PlastiquePolluant((int) (qte / 100.0));
    }
}
