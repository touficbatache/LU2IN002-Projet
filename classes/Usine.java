
/**
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 *
 * Usine qui gère plusieurs transformations :
 * - la création du plastique à partir du pétrole,
 * - le recyclage du plastique polluant en plastique bio-dégradable
 * 
 */

import java.util.ArrayList;

public class Usine {
    private Terrain terrain;

    private ArrayList<TechnicienPetrolier> techniciens = new ArrayList<TechnicienPetrolier>();
    private int qtePetrole = 0;

    private ArrayList<TravailleurUsine> travailleurs = new ArrayList<TravailleurUsine>();

    private int qteRecycle = 0;
    private int qteMaxRecyclable;
    private ArrayList<Plastique> liste_P = new ArrayList<Plastique>();

    public Usine(Terrain terrain, int qteMaxRecyclable) {
        this.terrain = terrain;
        this.qteMaxRecyclable = qteMaxRecyclable;
    }

    public ArrayList<TechnicienPetrolier> getTechniciens() {
        return techniciens;
    }

    public void addTechniciens(ArrayList<TechnicienPetrolier> tps) {
        techniciens.addAll(tps);
    }

    public void runTechniciens() {
        for (TechnicienPetrolier technicien : techniciens) {
            System.out.println("\nLe Téchnicien numéro " + technicien.ident + " parcourt le terrain.");
            for (int i = 0; i < Simulation.terrainSize[0] && !technicien.estPlein(); i++) {
                for (int j = 0; j < Simulation.terrainSize[1] && !technicien.estPlein(); j++) {
                    if (terrain.getCase(i, j) instanceof Petrole) {
                        Petrole p = (Petrole) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + p.getQuantite() + "L de pétrole en (" + p.getX() + ", "
                                + p.getY() + ")");
                    }
                    technicien.seDeplacer(i, j);
                    boolean collecteSucces = true;
                    while (collecteSucces) {
                        StatutReponse collecteResultat = technicien.collecter();
                        if (collecteResultat.succes) {
                            System.out.println(collecteResultat.message);
                        }
                        collecteSucces = collecteResultat.succes;
                    }
                }
            }

            terrain.affiche(7);
		    System.out.println("Informations sur le terrain:\n" + terrain + "\n");
        }
    }

    public void removeTechniciens() {
        techniciens.clear();
    }

    /**
     * Depose le pétrole dans l'usine pour qu'il puisse être recyclé par la suite.
     * 
     * @param qte la quantité de pétrole à déposer
     * 
     */
    public void deposerPetrole(int qte) {
        qtePetrole += qte;
    }

    /**
     * Produit du plastique à partir du pétrole déposé auparavant dans l'usine.
     * 
     * @return le plastique polluant produit
     * 
     */
    public PlastiquePolluant produirePlastique() {
        int qte = qtePetrole;
        qtePetrole = 0;
        return new PlastiquePolluant((int) (qte / 100.0));
    }

    public ArrayList<TravailleurUsine> getTravailleurs() {
        return travailleurs;
    }

    public void addTravailleurs(ArrayList<TravailleurUsine> tus) {
        travailleurs.addAll(tus);
    }

    public void runTravailleurs() {
        for (TravailleurUsine travailleur : travailleurs) {
            System.out.println("\nLe Travailleur numéro " + travailleur.ident + " parcourt le terrain.");
            for (int i = 0; i < Simulation.terrainSize[0] && !travailleur.estPlein(); i++) {
                for (int j = 0; j < Simulation.terrainSize[1] && !travailleur.estPlein(); j++) {
                    if (terrain.getCase(i, j) instanceof PlastiquePolluant) {
                        PlastiquePolluant tPP = (PlastiquePolluant) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + tPP.getQuantite() + "kg de plastique en (" + tPP.getX()
                                + ", " + tPP.getY() + ")");
                    }
                    travailleur.seDeplacer(i, j);
                    boolean collecteSucces = true;
                    while (collecteSucces) {
                        StatutReponse collecteResultat = travailleur.collecter();
                        if (collecteResultat.succes) {
                            System.out.println(collecteResultat.message);
                        }
                        collecteSucces = collecteResultat.succes;
                    }
                }
            }

            terrain.affiche(7);
		    System.out.println("Informations sur le terrain:\n" + terrain + "\n");
        }
    }
    
    public void removeTravailleurs() {
        travailleurs.clear();
    }
    
    public ArrayList<PlastiquePolluant> videRamassage() {
        ArrayList<PlastiquePolluant> liste = new ArrayList<PlastiquePolluant>();
		for (TravailleurUsine tu : travailleurs) {
			liste.addAll(tu.videCollecte());
		}
        return liste;
    }

    public void recyclerTout() {
        if (qteRecycle >= qteMaxRecyclable) {
            return;
        }

        for (PlastiquePolluant pp : videRamassage()) {
            pp.recyclage();
            System.out.println(pp.toString());
            qteRecycle++;
        }
    }

    public ArrayList<Plastique> ajouterLifeCycle(Terrain t) {
        for (int i = 0; i < t.nbLignes; i++) {
            for (int j = 0; j < t.nbColonnes; j++) {
                if (t.getCase(i, j) instanceof Plastique) {
                    liste_P.add((Plastique) t.getCase(i, j));
                }
            }
        }
        return liste_P;
    }

    public int qteP() {
        return liste_P.size();
    }

    public void allRecyclage(TravailleurUsine tU) {
        if (qteRecycle < qteMaxRecyclable) {
            tU.collecterPlastique();
            for (PlastiquePolluant p : tU.videCollecte()) {
                ((PlastiquePolluant) p).recyclage();
                System.out.println(p.toString());
                qteRecycle++;
            }
        }
    }

    public int getQteRecycle() {
        return qteRecycle;
    }

    public void allAugmenteAge() {
        for (Plastique p : this.liste_P) {
            p.augmenteAge();
        }
    }

    public void afficheListe() {
        for (Plastique p : liste_P) {
            System.out.println(p.toString() + "\t");
        }
    }

    @Override
    public String toString() {
        return "On a trouvé " + qteP() + "kg de plastique polluant sur le terrain" +
        " dont " + getQteRecycle() + "kg ont été recyclés ! ";
    }
}
