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
    private ArrayList<PlastiquePolluant> plastiqueRamasse;

    private final ArrayList<PlastiquePolluant> pps = new ArrayList<PlastiquePolluant>();

    private final ArrayList<PlastiqueBioDegradable> pbds;
    private final ArrayList<PlastiqueBioDegradable> pbdsATraiter;

    private Simulation() {
        this.terrain = new Terrain(SIZE_TERRAIN[0], SIZE_TERRAIN[1]);
        nbPetrole = randEntre(3, 5);
        nbTPs = randEntre(3, 4);
        nbTUs = randEntre(5, 7);

        techniciens = new ArrayList<TechnicienPetrolier>();
        totalExtraction = 0;

        travailleurs = new ArrayList<TravailleurUsine>();
        plastiqueRamasse = new ArrayList<PlastiquePolluant>();

        pbds = new ArrayList<PlastiqueBioDegradable>();
        pbdsATraiter = new ArrayList<PlastiqueBioDegradable>();
    }

    // Méthodes terrain

    /**
     * Affiche le terrain.
     *
     * @param nbCaracteres le nombre de caractères que doit afficher chaque case
     */
    public void afficheTerrain(int nbCaracteres) {
        terrain.affiche(nbCaracteres);
//        System.out.println("Informations sur le terrain:\n" + terrain + "\n");
    }

    /**
     * Affiche le terrain avec 7 caractères.
     */
    public void afficheTerrain() {
        afficheTerrain(7);
    }

    /**
     * Renvoie le nombre de cases remplies sur le terrain.
     *
     * @return le nombre de cases remplies sur le terrain
     */
    public int getCasesRemplies() {
        return terrain.lesRessources().size();
    }

    /**
     * Crée et affiche (si demandé) les Techniciens Pétroliers.
     *
     * @param affiche si la méthode doit afficher les techniciens
     */
    public void createTechniciens(boolean affiche) {
        if (affiche) System.out.println("Nos Techniciens Pétroliers :");

        for (int i = 0; i < nbTPs; i++) {
            int capaciteDeCollecte = randEntre(75, 150);
            int capaciteDeBaril = randEntre(30, 50);
            int nbBarils = randEntre(5, 10);
            techniciens.add(new TechnicienPetrolier(capaciteDeCollecte, capaciteDeBaril, nbBarils, terrain));
            if (affiche) System.out.println(techniciens.get(i));
        }

        if (affiche) System.out.println();
    }

    /**
     * Affiche les Techniciens Pétroliers.
     */
    public void afficheTechniciens() {
        for (TechnicienPetrolier tp : techniciens) {
            System.out.println(tp);
        }
    }

    /**
     * Fait parcourir le terrain aux Techniciens Pétroliers,
     * qui extraient du pétrole s'ils en trouvent.
     */
    public void runTechniciens() {
        for (TechnicienPetrolier technicien : techniciens) {
            System.out.println("Le Technicien numéro " + technicien.ident + " parcourt le terrain.");
            terrainParcours:
            for (int i = technicien.getPosX(); i < SIZE_TERRAIN[0] && !technicien.estPlein(); i++) {
                technicien.seDeplacer(i, 0);
                for (int j = technicien.getPosY(); j < SIZE_TERRAIN[1] && !technicien.estPlein(); j++) {
                    if (!estDeplacementPossible(technicien, i, j)) {
                        System.out.println("Oops, un autre agent se trouve sur (" + i + "," + j + "), je saute cette case.");
                        continue;
                    }

                    technicien.seDeplacer(i, j);
                    if (terrain.getCase(i, j) instanceof Petrole) {
                        Petrole p = (Petrole) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + p.getQuantite() + "L de pétrole en (" + p.getX() + ", "
                                + p.getY() + ")");
                    }
                    try {
                        int collecte = technicien.collecter();
                        if (collecte != -1) {
                            System.out.println("Je viens de collecter " + collecte + "L de pétrole. Mes barils contiennent " + technicien.getQuantiteCollectee() + "L en tout.");
                            break terrainParcours;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            afficheTerrain();
        }
    }

    /**
     * Vérifie si la case où se déplace l'agent est vide ou pas.
     *
     * @param agent l'agent en question
     * @param x coordonnées x de la case
     * @param y coordonnées y de la case
     * @return si la case où se déplace l'agent est vide
     */
    private boolean estDeplacementPossible(Agent agent, int x, int y) {
        for (TechnicienPetrolier tp : techniciens) {
            if (tp.ident != agent.ident && tp.getPosX() == x && tp.getPosY() == y) return false;
        }
        for (TravailleurUsine tu : travailleurs) {
            if (tu.ident != agent.ident && tu.getPosX() == x && tu.getPosY() == y) return false;
        }
        return true;
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
            int qte = randEntre(200, 300); // En litres
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
        int nbPlastiqueProduit = (int) (totalExtraction / 10.0);
        for (int i = 0; i < nbPlastiqueProduit; i++) {
            int randQte = randEntre(3, 5);
            int ageLimiteDeRecyclage = randEntre(3, 6);
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
            int capaciteDeCollecte = randEntre(5, 8);
            int capaciteDeStockage = randEntre(7, 13);
            travailleurs.add(new TravailleurUsine(capaciteDeCollecte, capaciteDeStockage, terrain));
            if (affiche) System.out.println(travailleurs.get(i));
        }

        if (affiche) System.out.println();
    }

    /**
     * Fait parcourir le terrain aux Travailleurs d'usine,
     * qui collectent du plastique polluant s'ils en trouvent.
     */
    public void runTravailleurs() {
        for (TravailleurUsine travailleur : travailleurs) {
            System.out.println("Le Travailleur numéro " + travailleur.ident + " parcourt le terrain.");
            terrainParcours:
            for (int i = travailleur.getPosX(); i < SIZE_TERRAIN[0] && !travailleur.estPlein(); i++) {
                travailleur.seDeplacer(i, 0);
                for (int j = travailleur.getPosY(); j < SIZE_TERRAIN[1] && !travailleur.estPlein(); j++) {
                    if (!estDeplacementPossible(travailleur, i, j)) {
                        System.out.println("Oops, un autre agent se trouve sur (" + i + "," + j + "), je saute cette case.");
                        continue;
                    }

                    travailleur.seDeplacer(i, j);
                    if (terrain.getCase(i, j) instanceof PlastiquePolluant) {
                        PlastiquePolluant tPP = (PlastiquePolluant) terrain.getCase(i, j);
                        System.out.println("J'ai trouvé " + tPP.getQuantite() + "kg de plastique en (" + tPP.getX()
                                + ", " + tPP.getY() + ")");
                    }
                    try {
                        int collecte = travailleur.collecter();
                        if (collecte != -1) {
                            System.out.println("Je viens de collecter " + collecte + "kg de plastique. J'ai " + travailleur.getQuantiteCollectee() + "kg en tout.");
                            break terrainParcours;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            afficheTerrain();
        }

        System.out.println("runTravailleurs size: " + pbds.size());
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
        plastiqueRamasse.clear();

        for (TravailleurUsine tu : travailleurs) {
            plastiqueRamasse.addAll(tu.getCollectes());
            tu.videCollecte();
        }

        if (plastiqueRamasse.isEmpty()) {
            throw new PasDeCollecteException("videRamassagePlastique", "runTravailleurs");
        }

        return plastiqueRamasse.size();
    }

    /**
     * Produit du plastique biodégradable à partir du plastique polluant total ramassé.
     *
     * @return plastiques biodégradables recyclés
     * @throws PasDeCollecteException s'il n'y a pas de collectes
     */
    public int recyclerTout() throws PasDeCollecteException {
        if (plastiqueRamasse.size() == 0) {
            throw new PasDeCollecteException("recyclerTout", "videRamassagePlastique");
        }

        for (PlastiquePolluant pp : plastiqueRamasse) {
            pbdsATraiter.add(new PlastiqueBioDegradable(pp));
        }

        plastiqueRamasse.clear();

        return pbdsATraiter.size();
    }

    /**
     * Rejette le plastique bio dans le terrain pour qu'il se décompose
     *
     * @param affiche si la méthode doit afficher les éléments
     */
    public void rejettePlastiquesBio(boolean affiche) {
        for (PlastiqueBioDegradable pbd : pbdsATraiter) {
            int x = randEntre(0, 4);
            int y = randEntre(0, 4);
            if (terrain.caseEstVide(x, y)) {
                pbds.add(pbd);
                pbd.setPosition(x, y);
                terrain.setCase(x, y, pbd);
//              if (affiche) System.out.println(pbd);
            }
        }
        pbdsATraiter.clear();
    }

    /**
     * Essaye de faire décomposer le plastique biodégradable.
     *
     * @return la quantité de plastique biodégradable décomposé
     */
    public int decomposerTout() {
        int qteDecomposee = 0;
        ArrayList<PlastiqueBioDegradable> aEnlever = new ArrayList<PlastiqueBioDegradable>();
        for (PlastiqueBioDegradable pbd : pbds) {
            if (pbd.estDecompositionPossible()) {
                terrain.videCase(pbd.getX(), pbd.getY());
                aEnlever.add(pbd);
                qteDecomposee++;
            }
        }
        pbds.removeAll(aEnlever);
        return qteDecomposee;
    }

    /**
     * Augmente l'âge de toutes les ressources.
     */
    public void augmenteAge() {
        ArrayList<Ressource> toutesRessources = new ArrayList<Ressource>();
        toutesRessources.addAll(pps);
        toutesRessources.addAll(pbds);
        for (Ressource r : toutesRessources) {
            if (r instanceof Plastique) ((Plastique) r).augmenteAge();
        }
    }

    /**
     * @param min la borne inférieure
     * @param max la borne supérieure
     * @return un nombre entier appartenant à [min, max]
     */
    public static int randEntre(int min, int max) {
        return (int) (Math.random() * Math.abs(max + 1 - min) + min);
    }

    @Override
    public String toString() {
        return "Simulation d'un terrain de " + SIZE_TERRAIN[0] + "x" + SIZE_TERRAIN[1] +
                " avec " + nbPetrole + " cases de pétrole et " + nbTPs + " travailleurs.";
    }
}
