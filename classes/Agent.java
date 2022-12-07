/**
 *
 * Représentation des agents qui parcourent le terrain.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 */


public abstract class Agent {
    private static int nbAgentsCrees = 0;
    public final int ident;
    public final String type;
    private final Terrain terrain;
    private int posX;
    private int posY;

/**
 * Constructeur qui initiliase le type de l'agent et le terrain sur lequel il se trouve
 * Affection du numéro ID unique
 * @param type type d'Agent
 * @param t terrain sur lequel se trouve l'Agent
 */
    public Agent(String type, Terrain t) {
        this.ident = Agent.nbAgentsCrees++;
        this.type = type;
        this.terrain = t;
    }

    /**
     * permet d'avoir acces au terrain sur lequel se trouve l'Agent
     * @return  le terrain sur lequel se trouve l'Agent
     */
    protected Terrain getTerrain() {
        return terrain;
    }

    /**
     * permet d'avoir acces a la ligne sur laquelle se trouve l'Agent
     * @return la ligne sur laquelle se trouve l'Agent
     */
    protected int getPosX() {
        return posX;
    }

    /**
     * permet d'avoir acces a la colonne sur laquelle se trouve l'Agent
     * @return  la colonne sur laquelle se trouve l'Agent
     */
    protected int getPosY() {
        return posY;
    }

    /**
     * calcule la distance entre l'Agent et une case donnée
     * @param x abscisse de la case concernée
     * @param y ordonnée de la case concernée
     * @return la distance entre l'Agent et la case concernée
     */
    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(posY - y, 2) + Math.pow(posX - x, 2));
    }

    /**
     * vérifie si les coordonnées sont présentes sur le terrain et déplace l'Agent dans la nouvelle case  si possible
     * @param xnew nouvelle abscisse
     * @param ynew nouvelle ordonnée
     * @return si l'Agent a été déplacé dans la nouvelle case
     */
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

    /**
     * Renvoie des informations sur l'Agent
     * @return l'ID et la position de l'Agent
     */
    @Override
    public String toString() {
        return type + "[id:" + ident + "] en position (" + posX + ", " + posY + ").";
    }
}
