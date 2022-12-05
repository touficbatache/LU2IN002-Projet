public class HayaTest {
    public static void main(String[] args) {
        Terrain t = new Terrain();

        PlastiquePolluant pp1 = new PlastiquePolluant(1);
        PlastiquePolluant pp2 = new PlastiquePolluant(1);
        PlastiquePolluant pp3 = new PlastiquePolluant(1);
        PlastiquePolluant pp4 = new PlastiquePolluant(1);
        PlastiquePolluant pp5 = new PlastiquePolluant(1);

        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp1);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp2);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp3);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp4);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp5);

        PlastiqueBioDegradable pbd1 = new PlastiqueBioDegradable(3);
        PlastiqueBioDegradable pbd2 = new PlastiqueBioDegradable(4);
        PlastiqueBioDegradable pbd3 = new PlastiqueBioDegradable(5);
        PlastiqueBioDegradable pbd4 = new PlastiqueBioDegradable(5);
        PlastiqueBioDegradable pbd5 = new PlastiqueBioDegradable(5);

        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd1);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd2);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd3);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd4);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd5);

        for(int i = 0; i < 20; i++) {
            if (Math.random() < 0.6) {
                t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), new PlastiquePolluant(1));
            } else {
                t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), new PlastiqueBioDegradable(3));
            }
        }

        t.affiche(3);

        pbd1.augmenteAge(); pbd2.augmenteAge(); pbd3.augmenteAge();
        pbd1.decomposition(t); pbd2.decomposition(t); pbd3.decomposition(t); pbd4.decomposition(t); 

        System.out.println(pbd1.toString()); System.out.println(pbd5.toString());

        pp1.recyclage(t);pp2.recyclage(t); pp3.recyclage(t);pp4.recyclage(t);
        System.out.println(pp1.toString()); System.out.println(pp2.toString());
        System.out.println("\n");

        t.affiche(3);

        Usine tab = new Usine(20);
        tab.ajouterLifeCycle(t);
        System.out.println("La liste des "+tab.qteP()+" plastiques présents sur le terrain : \n");
        tab.afficheListe();
        System.out.println("\n");

        tab.allRecyclage(t);
        System.out.println("\n");
        System.out.println("Terrain apres le recyclage des "+tab.getQteRecycle()+" plastiques polluants : \n");
        t.affiche(3);

        tab.allAugmenteAge();
        tab.allDecomposition(t);
        System.out.println("\n");
        System.out.println("Terrain apres la decomposition des "+ tab.getQteDecomp()+" plastiques biodegradables : \n");
        t.affiche(3);
    }
}
