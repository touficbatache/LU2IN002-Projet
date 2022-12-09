/**
 * 
 * Représentation des gisements de pétrole présents sur le terrain.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 */

public class Petrole extends Ressource {
    /**
     * Constructeur qui initialise la quantité de pétrole
     * @param quantite quantité de pétrole
     */
    public Petrole(int quantite) {
        super("Pétrole", quantite);
    }
}
