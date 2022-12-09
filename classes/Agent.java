/**
 * Représentation des agents qui parcourent le terrain.
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public abstract class Agent {
    private static int nbAgentsCrees = 0;
    public final int ident;
    public final String type;
    private final Terrain terrain;
    private int posX;
    private int posY;

    /**
     * Constructeur qui initialise l'agent avec un type et un terrain
     * Affection du numéro ID unique
     *
     * @param type type d'Agent
     * @param t    terrain sur lequel se trouve l'Agent
     */
    public Agent(String type, Terrain t) {
        this.ident = Agent.nbAgentsCrees++;
        this.type = type;
        this.terrain = t;
    }

    /**
     * Permet d'avoir accès au terrain sur lequel se trouve l'Agent
     *
     * @return le terrain sur lequel se trouve l'Agent
     */
    protected Terrain getTerrain() {
        return terrain;
    }

    /**
     * Permet d'avoir accès à la ligne sur laquelle se trouve l'Agent
     *
     * @return la ligne sur laquelle se trouve l'Agent
     */
    protected int getPosX() {
        return posX;
    }

    /**
     * Permet d'avoir accès a la colonne sur laquelle se trouve l'Agent
     *
     * @return la colonne sur laquelle se trouve l'Agent
     */
    protected int getPosY() {
        return posY;
    }

    /**
     * Calcule la distance entre l'Agent et une case donnée
     *
     * @param x abscisse de la case concernée
     * @param y ordonnée de la case concernée
     * @return la distance entre l'Agent et la case concernée
     */
    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(posY - y, 2) + Math.pow(posX - x, 2));
    }

    /**
     * Vérifie si les coordonnées sont présentes sur le terrain et déplace l'Agent dans la nouvelle case
     *
     * @param xnew nouvelle abscisse
     * @param ynew nouvelle ordonnée
     * @return si l'Agent a été déplacé dans la nouvelle case
     */
    public boolean seDeplacer(int xnew, int ynew) {
        posX = xnew;
        posY = ynew;
        return true;
    }

    /**
     * Renvoie des informations sur l'Agent
     *
     * @return l'ID et la position de l'Agent
     */
    @Override
    public String toString() {
        return type + "[id:" + ident + "] en position (" + posX + ", " + posY + ").";
    }
}
