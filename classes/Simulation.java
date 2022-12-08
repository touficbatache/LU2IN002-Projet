import java.util.ArrayList;

/**
 * Simulation du terrain. Elle contient et modifie plusieurs transformations :
 * <ul>
 *  <li>la création d'une liste de {@link TechnicienPetrolier}</li>
 *  <li>la création d'une liste de {@link TravailleurUsine}</li>
 *  <li>la creation de {@link Petrole}</li>
 *  <li>la création du {@link PlastiquePolluant} à partir du pétrole,</li>
 *  <li>le recyclage du plastique polluant en {@link PlastiqueBioDegradable}, et</li>
 *  <li>la décomposition du plastique biodégradable jeté dans le terrain.</li>
 * </ul>
 *
 * @author Toufic BATACHE (LU2IN002 2022dec)
 * @author Haya MAMLOUK (LU2IN002 2022dec)
 */

public class Simulation {
    private static Simulation INSTANCE;

    public static Simulation getInstance() {
        if (INSTANCE == null) INSTANCE = new Simulation();
        return INSTANCE;
    }

    private final int[] SIZE_TERRAIN = {10, 10};
    private final Terrain terrain;
    private final int nbPetrole;
    private final int nbTPs;
    private final int nbTUs;

    private final ArrayList<TechnicienPetrolier> techniciens;
    private int totalExtraction;

    private final ArrayList<TravailleurUsine> travailleurs;
    private int totalRamassage;

    private final ArrayList<PlastiqueBioDegradable> pbds;

    private final ArrayList<PlastiquePolluant> pps = new ArrayList<PlastiquePolluant>();
    private int qteDecomposee;

    private Simulation() {
        this.terrain = new Terrain(SIZE_TERRAIN[0], SIZE_TERRAIN[1]);
        nbPetrole = randEntre(2, 6);
        nbTPs = randEntre(2, 6);
        nbTUs = randEntre(3, 6);

        techniciens = new ArrayList<TechnicienPetrolier>();
        totalExtraction = 0;

        travailleurs = new ArrayList<TravailleurUsine>();
        totalRamassage = 0;

        pbds = new ArrayList<PlastiqueBioDegradable>();
        qteDecomposee = 0;
    }

    // Méthodes terrain

    /**
     * Affiche le terrain.
     *
     * @param nbCaracteres le nombre de caractères que doit afficher chaque case
     */
    public void afficheTerrain(int nbCaracteres) {
        terrain.affiche(nbCaracteres);
        System.out.println("Informations sur le terrain:\n" + terrain + "\n");
    }

    /**
     * Affiche le terrain avec 7 caractères.
     */
    public void afficheTerrain() {
        afficheTerrain(7);
    }

    /**
     * Crée et affiche (si demandé) les Techniciens Pétroliers.
     *
     * @param affiche si la méthode doit afficher les techniciens
     */
    public void createTechniciens(boolean affiche) {
        if (affiche) System.out.println("Nos Techniciens Pétroliers :");

        for (int i = 0; i < nbTPs; i++) {
            int capaciteDeCollecte = randEntre(20, 40);
            int capaciteDeBaril = randEntre(30, 50);
            int nbBarils = randEntre(5, 10);
            techniciens.add(new TechnicienPetrolier(capaciteDeCollecte, capaciteDeBaril, nbBarils, terrain));
            if (affiche) System.out.println(techniciens.get(i));
        }
    }

    /**
     * Fait parcourir le terrain aux Techniciens Pétroliers,
     * qui extraient du pétrole s'ils en trouvent.
     */
    public void runTechniciens() {
        for (TechnicienPetrolier technicien : techniciens) {
            System.out.println("\nLe Technicien numéro " + technicien.ident + " parcourt le terrain.");
            for (int i = 0; i < SIZE_TERRAIN[0] && !technicien.estPlein(); i++) {
                for (int j = 0; j < SIZE_TERRAIN[1] && !technicien.estPlein(); j++) {
                    if (terrain.getCase(i, j) instanceof Petrole) {
                        Petrole p = (Petrole) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + p.getQuantite() + "L de pétrole en (" + p.getX() + ", "
                                + p.getY() + ")");
                    }
                    technicien.seDeplacer(i, j);
                    boolean collecteSucces = true;
                    while (collecteSucces) {
                        try {
                            int collecte = technicien.collecter();
                            if (collecte == -1) {
                                collecteSucces = false;
                            } else {
                                System.out.println("Je viens de collecter " + collecte + "L de pétrole. Mes barils contiennent " + technicien.getQuantiteCollectee() + "L en tout.");
                            }
                        } catch (Exception e) {
                            System.out.println();
                            collecteSucces = false;
                        }
                    }
                }
            }

            afficheTerrain();
        }
    }

    /**
     * Vide la liste de techniciens.
     */
    public void removeTechniciens() {
        techniciens.clear();
    }

    /**
     * Ajouter le pétrole au terrain.
     */
    public void createPetrole() {
        for (int i = 0; i < nbPetrole; i++) {
            int x = randEntre(0, 4);
            int y = randEntre(5, 9);
            int qte = randEntre(300, 400); // En litres
            Petrole petrole = new Petrole(qte);
            petrole.setPosition(x, y);
            terrain.setCase(x, y, petrole);
        }
    }

    /**
     * Vide la totalité du pétrole stocké dans les barils des techniciens.
     *
     * @return le volume de pétrole total extrait par les techniciens
     * @throws PasDeCollecteException s'il n'y a pas de collectes
     */
    public int videExtractionPetrole() throws PasDeCollecteException {
        int qteExtraction = 0;
        for (TechnicienPetrolier tp : techniciens) {
            qteExtraction += tp.videCollecte();
        }
        if (qteExtraction == 0) {
            throw new PasDeCollecteException("videExtractionPetrole", "runTechniciens");
        }
        totalExtraction += qteExtraction;
        return qteExtraction;
    }

    /**
     * Produit du plastique polluant à partir du pétrole total extrait.
     *
     * @throws PasDeCollecteException s'il n'y a pas de collectes
     */
    public void produirePlastique() throws PasDeCollecteException {
        if (totalExtraction == 0) {
            throw new PasDeCollecteException("produirePlastique", "videExtractionPetrole");
        }
        int nbPlastiqueProduit = (int) (totalExtraction / 100.0);
        for (int i = 0; i < nbPlastiqueProduit; i++) {
            int randQte = (int) (Math.random() * 4 + 3); // entre 3 et 7
            int ageLimiteDeRecyclage = randEntre(5, 7);
            pps.add(new PlastiquePolluant(randQte, ageLimiteDeRecyclage));
        }
        totalExtraction = 0;
    }

    /**
     * Pollue le terrain avec des plastiques polluants.
     *
     * @param affiche si la méthode doit afficher les éléments
     */
    public void polluer(boolean affiche) {
        for (PlastiquePolluant pp : pps) {
            int x = randEntre(5, 9);
            int y = randEntre(0, 4);
            pp.setPosition(x, y);
            terrain.setCase(x, y, pp);
            if (affiche) System.out.println(pp);
        }
        pps.clear();
    }

    /**
     * Crée et affiche (si demandé) les Travailleurs d'usine.
     *
     * @param affiche si la méthode doit afficher les travailleurs
     */
    public void createTravailleurs(boolean affiche) {
        if (affiche) System.out.println("Nos Travailleurs à l'usine :");

        for (int i = 0; i < nbTUs; i++) {
            int capaciteDeCollecte = randEntre(1, 1);
            int capaciteDeStockage = randEntre(7, 13);
            travailleurs.add(new TravailleurUsine(capaciteDeCollecte, capaciteDeStockage, terrain));
            if (affiche) System.out.println(travailleurs.get(i));
        }
    }

    /**
     * Fait parcourir le terrain aux Travailleurs d'usine,
     * qui collectent du plastique polluant s'ils en trouvent.
     */
    public void runTravailleurs() {
        for (TravailleurUsine travailleur : travailleurs) {
            System.out.println("\nLe Travailleur numéro " + travailleur.ident + " parcourt le terrain.");
            for (int i = 0; i < SIZE_TERRAIN[0] && !travailleur.estPlein(); i++) {
                for (int j = 0; j < SIZE_TERRAIN[1] && !travailleur.estPlein(); j++) {
                    if (terrain.getCase(i, j) instanceof PlastiquePolluant) {
                        PlastiquePolluant tPP = (PlastiquePolluant) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + tPP.getQuantite() + "kg de plastique en (" + tPP.getX()
                                + ", " + tPP.getY() + ")");
                    }
                    travailleur.seDeplacer(i, j);
                    boolean collecteSucces = true;
                    while (collecteSucces) {
                        try {
                            int collecte = travailleur.collecter();
                            if (collecte == -1) {
                                collecteSucces = false;
                            } else {
                                System.out.println("Je viens de collecter " + collecte + "kg de plastique. J'ai " + travailleur.getQuantiteCollectee() + "kg en tout.");
                            }
                        } catch (Exception e) {
                            System.out.println();
                            collecteSucces = false;
                        }
                    }
                }
            }

            afficheTerrain();
        }
    }

    /**
     * Vide la liste de travailleurs.
     */
    public void removeTravailleurs() {
        travailleurs.clear();
    }

    /**
     * Vide la totalité du plastique polluant stocké avec les travailleurs.
     *
     * @return le volume de plastique polluant total collecté par les travailleurs
     * @throws PasDeCollecteException s'il n'y a pas de collectes
     */

    public int videRamassagePlastique() throws PasDeCollecteException {
        int qteRamassage = 0;
        for (TravailleurUsine tu : travailleurs) {
            qteRamassage += tu.videCollecte();
        }
        if (qteRamassage == 0) {
            throw new PasDeCollecteException("videRamassagePlastique", "runTravailleurs");
        }
        totalRamassage += qteRamassage;
        return qteRamassage;
    }

    /**
     * Produit du plastique biodégradable à partir du plastique polluant total ramassé.
     *
     * @throws PasDeCollecteException s'il n'y a pas de collectes
     */
    public void recyclerTout() throws PasDeCollecteException {
        if (totalRamassage == 0) {
            throw new PasDeCollecteException("recyclerTout", "videRamassagePlastique");
        }
        int step = (int) (Math.random() * 2 + 2); // entre 2 et 4
        int nbPlastiqueRamasse = totalRamassage;
        int nbPBD = (int) Math.ceil((double) nbPlastiqueRamasse / step);
        for (int i = 0; i < nbPBD; i++) {
            int qte = Math.min(nbPlastiqueRamasse, step);
            int dureeDeVie = randEntre(3, 5);
            pbds.add(new PlastiqueBioDegradable(qte, dureeDeVie));
            nbPlastiqueRamasse -= qte;
        }
        totalRamassage = 0;
    }

    /**
     * Rejette le plastique bio dans le terrain pour qu'il se décompose
     *
     * @param affiche si la méthode doit afficher les éléments
     */
    public void rejettePlastiquesBio(boolean affiche) {
        for (PlastiqueBioDegradable pbd : pbds) {
            int x = randEntre(0, 4);
            int y = randEntre(0, 4);
            pbd.setPosition(x, y);
            terrain.setCase(x, y, pbd);
            if (affiche) System.out.println(pbd);
        }
    }

    /**
     * Essaye de faire décomposer le plastique biodégradable.
     */
    public void decomposerTout() {
        for (PlastiqueBioDegradable pbd : pbds) {
            pbd.augmenteAge();
            if (pbd.estDecompositionPossible()) {
                terrain.videCase(pbd.getX(), pbd.getY());
            }
            pbds.remove(pbd);
            qteDecomposee++;
        }
    }

    /**
     * @return la quantité de plastique biodégradable décomposé
     */
    public int getQteDecomposee() {
        return qteDecomposee;
    }

    /**
     * @param min la borne inférieure
     * @param max la borne supérieure
     * @return un nombre entier appartenant à [min, max]
     */
    private int randEntre(int min, int max) {
        return (int) (Math.random() * Math.abs(max + 1 - min) + min);
    }

    @Override
    public String toString() {
        return "Simulation d'un terrain de " + SIZE_TERRAIN[0] + "x" + SIZE_TERRAIN[1] +
                " avec " + nbPetrole + " cases de pétrole et " + nbTPs + " travailleurs.";
    }
}
