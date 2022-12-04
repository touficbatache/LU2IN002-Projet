public class Ressource {
    private static int nbRessourcesCreees = 0;
    public final int ident;
    public final String type;
    private int quantite;
    private int x;
    private int y;
    
    public Ressource(final String type, final int quantite) {
        this.type = type;
        this.quantite = quantite;
        this.ident = Ressource.nbRessourcesCreees++;
        this.x = -1;
        this.y = -1;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(final int quantite) {
        this.quantite = quantite;
    }
    
    public void setPosition(final int lig, final int col) {
        this.x = lig;
        this.y = col;
    }
    
    public void initialisePosition() {
        this.x = -1;
        this.y = -1;
    }
    
    @Override
    public String toString() {
        String sortie = String.valueOf(this.type) + "[id:" + this.ident + " quantite: " + this.quantite + "] ";
        if (this.x == -1 || this.y == -1) {
            sortie = String.valueOf(sortie) + " n'est pas sur le terrain.";
        }
        else {
            sortie = String.valueOf(sortie) + " en position (" + this.x + ", " + this.y + ")";
        }
        return sortie;
    }
}