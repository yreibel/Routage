package elements;

/**
 * Classe AreteOrdiCommu
 * Représente l'arête entre un ordinateur et un commutateur
 */
public class AreteOrdiCommu {

    private Ordinateur extremite1;
    private Commutateur extremite2;


    /**
     * Constructeur AreteOrdiCommu
     * @param extremite1
     * @param extremite2
     */
    public AreteOrdiCommu(Ordinateur extremite1, Commutateur extremite2){
        this.extremite1 = extremite1;
        this.extremite2 = extremite2;
    }

    /**
     * Retourne l'extrémite n°1
     * @return Ordinateur
     */
    public Ordinateur getExtremite1() {
        return extremite1;
    }

    /**
     * Retourne l'extrémité n°2
     * @return Commutateur
     */
    public Commutateur getExtremite2() {
        return extremite2;
    }
}
