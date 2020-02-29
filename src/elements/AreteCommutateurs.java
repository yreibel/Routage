package elements;

public class AreteCommutateurs {

    private Commutateur extremite1;
    private Commutateur extremite2;

    private int distance;

    /**
     * Constructeur Arete
     * @param extremite1
     * @param extremite2
     */
    public AreteCommutateurs(Commutateur extremite1, Commutateur extremite2){

        this.extremite1 = extremite1;
        this.extremite2 = extremite2;

        this.distance = this.calculDistanceEntreNoeuds(extremite1, extremite2);
    }

    /**
     * Retourne l'extrémité numéro 1
     * @return Equipement
     */
    public Commutateur getExtremite1(){
        return this.extremite1;
    }

    /**
     * Retourne l'extrémité numéro 2
     * @return Equipement
     */
    public Commutateur getExtremite2(){
        return this.extremite2;
    }

    /**
     * Retourne la distance entre les deux noeuds
     * @return int
     */
    public int calculDistanceEntreNoeuds(Commutateur extremite1, Commutateur extremite2){
       int dist1 = (int) extremite1.getRouteursVoisins().get(extremite2);

       return dist1;
    }

    /**
     * Retourne la distance entre les deux noeuds
     * @return int
     */
    public int getDistance(){
        return this.distance;
    }
}
