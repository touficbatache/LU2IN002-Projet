public interface Collecteur {
    public StatutReponse collecter();

    public int getCapaciteDeStockage();

    public int getQuantiteCollectee();

    public boolean estPlein();
}
