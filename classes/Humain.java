/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 * Représentation des humains, agents qui parcourent le terrain.
 *
 */

public abstract class Humain extends Agent {
    private String travail;
    
    public Humain(String job, Terrain t) {
        super("Humain", t);

        this.travail = job;
    }

    @Override
    public String toString() {
        return travail + " - " + super.toString();
    }
}
