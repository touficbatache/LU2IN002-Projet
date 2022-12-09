import java.util.ArrayList;

public final class Terrain {
    public static final int NBLIGNESMAX = 20;
    public static final int NBCOLONNESMAX = 20;
    public final int nbLignes;
    public final int nbColonnes;
    private Ressource[][] terrain;
    
    public Terrain() {
        this(NBLIGNESMAX, NBCOLONNESMAX);
    }
    
    public Terrain(final int nblig, final int nbcol) {
        if (nblig > NBLIGNESMAX) {
            this.nbLignes = NBLIGNESMAX;
        } else if (nblig <= 0) {
            this.nbLignes = 1;
        } else {
            this.nbLignes = nblig;
        }

        if (nbcol > NBCOLONNESMAX) {
            this.nbColonnes = NBCOLONNESMAX;
        } else if (nbcol <= 0) {
            this.nbColonnes = 1;
        } else {
            this.nbColonnes = nbcol;
        }

        this.terrain = new Ressource[this.nbLignes][this.nbColonnes];
    }
    
    public Ressource getCase(final int lig, final int col) {
        if (this.sontValides(lig, col)) {
            return this.terrain[lig][col];
        }
        return null;
    }
    
    public Ressource videCase(final int lig, final int col) {
        if (this.sontValides(lig, col) && this.terrain[lig][col] != null) {
            final Ressource elt = this.terrain[lig][col];
            elt.initialisePosition();
            this.terrain[lig][col] = null;
            return elt;
        }
        return null;
    }
    
    public boolean setCase(final int lig, final int col, final Ressource ress) {
        if (this.sontValides(lig, col)) {
            if (this.terrain[lig][col] != null) {
                this.terrain[lig][col].initialisePosition();
            }
            (this.terrain[lig][col] = ress).setPosition(lig, col);
            return true;
        }
        return false;
    }
    
    public boolean caseEstVide(final int lig, final int col) {
        return !this.sontValides(lig, col) || this.terrain[lig][col] == null;
    }
    
    public ArrayList<Ressource> lesRessources() {
        final ArrayList<Ressource> list = new ArrayList<Ressource>();
        for (int lig = 0; lig < this.nbLignes; ++lig) {
            for (int col = 0; col < this.nbColonnes; ++col) {
                if (this.terrain[lig][col] != null) {
                    list.add(this.terrain[lig][col]);
                }
            }
        }
        return list;
    }
    
    public boolean sontValides(final int lig, final int col) {
        return lig >= 0 && lig < this.nbLignes && col >= 0 && col < this.nbColonnes;
    }
    
    public void affiche(final int nbCaracteres) {
        String sortie = "";
        String cadre = ":";
        String ligne = "";
        final int nbCar = Math.max(nbCaracteres, 1);
        for (int i = 0; i < nbCar; ++i) {
            ligne = String.valueOf(ligne) + "-";
        }
        for (int j = 0; j < this.nbColonnes; ++j) {
            cadre = String.valueOf(cadre) + ligne + ":";
        }
        cadre = (sortie = String.valueOf(cadre) + "\n");
        for (int lig = 0; lig < this.nbLignes; ++lig) {
            for (int col = 0; col < this.nbColonnes; ++col) {
                if (this.terrain[lig][col] == null) {
                    sortie = String.valueOf(sortie) + "|" + String.format("%-" + nbCar + "s", " ");
                }
                else {
                    sortie = String.valueOf(sortie) + "|" + this.premiersCar(this.terrain[lig][col].type, nbCar);
                }
            }
            sortie = String.valueOf(sortie) + "|\n" + cadre;
        }
        System.out.println(sortie);
    }
    
    @Override
    public String toString() {
        int compte = 0;
        for (int i = 0; i < this.nbLignes; ++i) {
            for (int j = 0; j < this.nbColonnes; ++j) {
                if (this.terrain[i][j] != null) {
                    ++compte;
                }
            }
        }
        String sortie = "Terrain de " + this.nbLignes + "x" + this.nbColonnes + " cases: ";
        if (compte == 0) {
            sortie = String.valueOf(sortie) + "toutes les cases sont libres.";
        }
        else if (compte == 1) {
            sortie = String.valueOf(sortie) + "il y a une case occup\u00e9e.";
        }
        else {
            sortie = String.valueOf(sortie) + "il y a " + compte + " cases occup\u00e9es.";
        }
        return sortie;
    }
    
    private String premiersCar(final String s, final int nbCar) {
        final String sExtended = String.format("%-" + nbCar + "s", s);
        return sExtended.substring(0, nbCar);
    }
}