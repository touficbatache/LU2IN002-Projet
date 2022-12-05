public class HayaTest {
    public static void main(String[] args) {
        Terrain t = new Terrain();

        PlastiquePolluant pp1 = new PlastiquePolluant();
        PlastiquePolluant pp2 = new PlastiquePolluant();
        PlastiquePolluant pp3 = new PlastiquePolluant();
        PlastiquePolluant pp4 = new PlastiquePolluant();
        PlastiquePolluant pp5 = new PlastiquePolluant();

        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp1);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp2);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp3);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp4);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pp5);

        PlastiqueBioDegradable pbd1 = new PlastiqueBioDegradable();
        PlastiqueBioDegradable pbd2 = new PlastiqueBioDegradable();
        PlastiqueBioDegradable pbd3 = new PlastiqueBioDegradable();
        PlastiqueBioDegradable pbd4 = new PlastiqueBioDegradable();
        PlastiqueBioDegradable pbd5 = new PlastiqueBioDegradable();

        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd1);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd2);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd3);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd4);
        t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), pbd5);

        for(int i = 0; i < 20; i++) {
            if (Math.random() < 0.6) {
                t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), new PlastiquePolluant());
            } else {
                t.setCase((int) (Math.random() * t.nbLignes), (int) (Math.random() * t.nbColonnes), new PlastiqueBioDegradable());
            }
        }

        t.affiche(3);
        
        pbd1.augmenteTemps(); pbd2.augmenteTemps(); pbd3.augmenteTemps();
        pbd1.decomposition(t); pbd2.decomposition(t); pbd3.decomposition(t); pbd4.decomposition(t); 

        System.out.println(pbd1.toString()); System.out.println(pbd5.toString());
        
        Recyclage rec = new Recyclage(20);
        rec.ajouterRecyclage(pp1, t); rec.ajouterRecyclage(pp2, t); rec.ajouterRecyclage(pp3, t); rec.ajouterRecyclage(pp4, t);
        System.out.println("La liste des plastiques recycles : \n");
        rec.afficheListe();
        System.out.println(pp5.toString());
        System.out.println(rec.toString());

        t.affiche(3);
    }
}
