public class Agent {
    private static int nbAgentsCrees = 0;
    public final int ident;
    public final String type;
    private final Terrain terrain;
    private int posX;
    private int posY;

    public Agent(String type, Terrain t, int x, int y) {
        this.ident = Agent.nbAgentsCrees++;
        this.type = type;
        this.terrain = t;
        this.posX = x;
        this.posY = y;
    }

    protected Terrain getTerrain() {
        return terrain;
    }

    protected int getPosX() {
        return posX;
    }

    protected int getPosY() {
        return posY;
    }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(posY - y, 2) + Math.pow(posX - x, 2));
    }

    public boolean seDeplacer(int xnew, int ynew) {
        // if (!terrain.sontValides(xnew, ynew)) {
        //     System.out.println("Case hors du terrain ! Essayez à nouveau...");
        //     return false;
        // }

        // if (!terrain.caseEstVide(xnew, ynew)) {
        //     System.out.println("Case déjà occupée ! Essayez à nouveau...");
        //     return false;
        // }

        // TODO: change la position de l’objet courant sur le terrain si c’est possible (case non occupée par un autre agent)

        posX = xnew;
        posY = ynew;
        return true;
    }

    @Override
    public String toString() {
        return type + "[id:" + ident + "] en position (" + posX + ", " + posY + ").";
    }
}
