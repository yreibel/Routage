package elements;

import java.util.ArrayList;

/**
 * Classe Topologie
 * @author Yann REIBEL L3 INFO
 */
public class TopologieReseau {

    private ArrayList<Equipement> listeEquipements;
    private ArrayList<AreteCommutateurs> listeAreteCommutateurs;
    private ArrayList<AreteOrdiCommu> listeAreteOrdiCommu;

    /**
     * Constructeur Topologie
     */
    public TopologieReseau(){
        this.listeEquipements = new ArrayList<>();
        this.listeAreteCommutateurs = new ArrayList<>();
        this.listeAreteOrdiCommu = new ArrayList<>();
    }


    /**
     * Crée une liste d'équipements (ordinateurs, commutateurs)
     * @param liste
     */
    public void creerListeEquipements(ArrayList<Equipement> liste){
        this.listeEquipements = liste;
    }

    /**
     * Crée une liste d'arêtes entre commutateurs
     * @param liste
     */
    public void creerListeAreteCommutateurs(ArrayList<AreteCommutateurs> liste){
        this.listeAreteCommutateurs = liste;
    }

    /**
     * Crée une liste d'arêtes entre un ordinateur et un commutateur
     * @param liste
     */
    public void creerListeAreteOrdiCommu(ArrayList<AreteOrdiCommu> liste){
        this.listeAreteOrdiCommu = liste;
    }

    /**
     * Ajoute un équipement à la liste d'équipements
     * @param equipement
     */
    public void ajouterEquipement(Equipement equipement){
        this.listeEquipements.add(equipement);
    }

    /**
     * Retire un équipement de la liste
     * @param equipement
     */
    public void retirerEquipement(Equipement equipement){
        this.listeEquipements.remove(equipement);
    }

    /**
     * Ajoute une arête (entre commutateurs) à la liste
     * @param areteCommutateurs
     */
    public void ajouterAreteEntreCommutateurs(AreteCommutateurs areteCommutateurs){
        this.listeAreteCommutateurs.add(areteCommutateurs);
    }

    /**
     * Retire une arête (entre commutateurs) de la liste
     * @param areteCommutateurs
     */
    public void retirerAreteEntreCommutateurs(AreteCommutateurs areteCommutateurs){
        this.listeAreteCommutateurs.remove(areteCommutateurs);
    }

    /**
     * Ajoute une arête (entre ordinateur et commutateur) à la liste
     * @param areteOrdiCommu
     */
    public void ajouterAreteOrdiCommu(AreteOrdiCommu areteOrdiCommu){
        this.listeAreteOrdiCommu.add(areteOrdiCommu);
    }

    /**
     * Retire une arête (entre ordinateur et commutateur) de la liste
     * @param areteOrdiCommu
     */
    public void retirerAreteOrdiCommu(AreteOrdiCommu areteOrdiCommu){
        this.listeAreteOrdiCommu.remove(areteOrdiCommu);
    }

}
