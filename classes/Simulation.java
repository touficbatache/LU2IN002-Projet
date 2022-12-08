/**
 *
 * Simulation du terrain. Elle contient et modifie plusieurs transformations :
 * -la réation d'une liste de TechnicienPetrolier
 * -la création d'une liste de TravailleurUsine
 * -la la creation de Petrole
 * - la création du plastique à partir du pétrole,
 * - le recyclage du plastique polluant en plastique bio-dégradable.
 * -la décomposition du plastique biodégradable jeter dans le terrain
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 * 
 */

import java.util.ArrayList;

public class Simulation {
    private Terrain terrain;

    private ArrayList<TechnicienPetrolier> techniciens = new ArrayList<TechnicienPetrolier>();

    private ArrayList<TravailleurUsine> travailleurs = new ArrayList<TravailleurUsine>();

    private ArrayList<PlastiqueBioDegradable> pbds= new ArrayList<PlastiqueBioDegradable>();

    private ArrayList<PlastiquePolluant> pps = new ArrayList<PlastiquePolluant>();

    private int qteRecycle = 0;
    private int qteMaxRecyclable;
    private int totalExtraction = 0;
    private int totalRamassage=0;

    private int nbTPs;
    private int nbPetrole;
    private int nbTUs;
    int qtetDecomp = 0;

    public Simulation(Terrain terrain, int qteMaxRecyclable) {
        this.terrain = terrain;
        this.qteMaxRecyclable = qteMaxRecyclable;
        nbTPs = randEntre(2, 6);
        nbPetrole = randEntre(2, 6);
        nbTUs = randEntre(3, 6);
    }

    public ArrayList<TechnicienPetrolier> getTechniciens() {
        return techniciens;
    }

    public void addTechniciens(ArrayList<TechnicienPetrolier> tps) {
        techniciens.addAll(tps);
    }

    public void createTechniciens(){
        for (int i = 0; i < nbTPs; i++) {
            int capaciteDeCollecte = randEntre(20, 40);
            int capaciteDeBarril = randEntre(30, 50);
            int nbBarrils = randEntre(5, 10);
            techniciens.add(new TechnicienPetrolier(capaciteDeCollecte, capaciteDeBarril, nbBarrils, terrain));
            System.out.println(techniciens.get(i));
        }
    }
    

    /**
     * Fait parcourir le terrain aux Téchniciens Pétroliers, qui collectent du
     * pétrole s'ils en trouvent.
     */
    public void runTechniciens() {
        for (TechnicienPetrolier technicien : techniciens) {
            System.out.println("\nLe Téchnicien numéro " + technicien.ident + " parcourt le terrain.");
            for (int i = 0; i < TestSimulation.terrainSize[0] && !technicien.estPlein(); i++) {
                for (int j = 0; j < TestSimulation.terrainSize[1] && !technicien.estPlein(); j++) {
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

    public void createPetrole(){
        for (int i = 0; i < nbPetrole; i++) {
            int x = randEntre(0, 4);
            int y = randEntre(5, 9);
            int qte = randEntre(300, 400); // En litres
            Petrole petrole = new Petrole(qte);
            petrole.setPosition(x, y);
            terrain.setCase(x, y, petrole);
        }
    }

    public void extractionPetrole(){
        for (TechnicienPetrolier tp : getTechniciens()) {
            System.out.println(tp);
            totalExtraction += tp.getQuantiteCollectee();
        }
    }

    /**
     * Vide la totalité du pétrole stocké dans les barrils des téchniciens.
     * 
     * @return le volume de pétrole total extrait par les téchniciens
     */
    public int videExtractionPetrole() {
        int qteTotalExtraction=totalExtraction;
        totalExtraction = 0;
        return qteTotalExtraction;
    }

    /**
     * Produit du plastique polluant à partir du pétrole déposé auparavant dans l'usine.
     * 
     * @return le plastique polluant produit
     * 
     */
    public void produirePlastique() {
        // TODO: throw some exception if no petrole in stock
        // if (videExtractionPetrole() == 0) {
        //     ...exception
        // }
        int nbPlastiqueProduit = (int) (videExtractionPetrole() / 100.0);
        for (int i = 0; i < nbPlastiqueProduit; i++) {
            int randQte = (int) (Math.random() * 4 + 3); // entre 3 et 7
            pps.add(new PlastiquePolluant(randQte));
        }
    }

    public void setPPS(){
        for (PlastiquePolluant pp : pps) {
            int x = randEntre(5, 9);
            int y = randEntre(0, 4);
            pp.setPosition(x, y);
            terrain.setCase(x, y, pp);
            System.out.println(pp);
        }
    }

    public ArrayList<TravailleurUsine> getTravailleurs() {
        return travailleurs;
    }

    public void addTravailleurs(ArrayList<TravailleurUsine> tus) {
        travailleurs.addAll(tus);
    }

    public void createTravailleurs(){
        
        for (int i = 0; i < nbTUs; i++) {
            int capaciteDeCollecte = randEntre(1, 1);
            int capaciteDeStockage = randEntre(7, 13);
            travailleurs.add(new TravailleurUsine(capaciteDeCollecte, capaciteDeStockage, terrain));
            System.out.println(travailleurs.get(i));
        }
    }

    /**
     * Fait parcourir le terrain aux Travailleurs d'usine, qui collectent du
     * plastique polluant s'ils en trouvent.
     */
    public void runTravailleurs() {
        for (TravailleurUsine travailleur : travailleurs) {
            System.out.println("\nLe Travailleur numéro " + travailleur.ident + " parcourt le terrain.");
            for (int i = 0; i < TestSimulation.terrainSize[0] && !travailleur.estPlein(); i++) {
                for (int j = 0; j < TestSimulation.terrainSize[1] && !travailleur.estPlein(); j++) {
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

    public  void ramassage(){
        for (TravailleurUsine tu : getTravailleurs()) {
            System.out.println(tu);
            totalRamassage += tu.getQuantiteCollectee();
        }
    }

    public int getTotalRamassage(){
        return totalRamassage;
    }
    
    public int videRamassagePlastique() {
        int qteTotalRamassage=totalRamassage;
        totalRamassage = 0;
        return qteTotalRamassage;
    }

    public void recyclerTout() {
        // TODO: throw exception here too
        // if (qteRecycle >= qteMaxRecyclable) {
        //     ...exception
        // }

        int step = (int) (Math.random() * 2 + 2); // entre 2 et 4
        int nbPlastiqueRamasse = videRamassagePlastique();
        int nbPBD = (int) Math.ceil((double) nbPlastiqueRamasse / step);
        for (int i = 0; i < nbPBD; i++) {
            int qte = Math.min(nbPlastiqueRamasse, step);
            pbds.add(new PlastiqueBioDegradable(qte));
            nbPlastiqueRamasse -= qte;
        }
    }

    public void setPBDS(){
        for (PlastiqueBioDegradable pbd : pbds) {
            int x = randEntre(0, 4);
            int y = randEntre(0, 4);
            pbd.setPosition(x, y);
            terrain.setCase(x, y, pbd);
            System.out.println(pbd);
        }
    }

    public void decomposerTout(){
        for (PlastiqueBioDegradable pbd : pbds) {
            pbd.augmenteAge();
            pbd.decomposition(terrain);
            qtetDecomp++;
        }
    }

    public int getQteDecomp(){
        return qtetDecomp;
    }

    public int getQteRecycle() {
        return qteRecycle;
    }

    private static int randEntre(int min, int max) {
        return (int) (Math.random() * Math.abs(max - min) + min);
    }

    @Override
    public String toString() {
        return getQteRecycle() + "kg de plastique polluant ont été recyclés !";
    }
}
