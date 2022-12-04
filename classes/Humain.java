public class Humain extends Agent {
    private String travail;
    
    public Humain(String job, Terrain t, int x, int y) {
        super("Humain", t, x, y);

        this.travail = job;
    }

    @Override
    public String toString() {
        return travail + " - " + super.toString();
    }
}
