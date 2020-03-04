package structure;

import structure.elements.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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

    /**
     * Retourne la liste d'équipements
     * @return ArrayList
     */
    public ArrayList<Equipement> getListeEquipements(){
        return this.listeEquipements;
    }

    /**
     * Retourne la liste comprenant les arêtes entre les commutateurs
     * @return ArrayList
     */
    public ArrayList<AreteCommutateurs> getListeAreteCommutateurs(){
        return this.listeAreteCommutateurs;
    }

    /**
     * Retourne la liste comprenant les arêtes entre un ordinateur et un commutateur
     * @return ArrayList
     */
    public ArrayList<AreteOrdiCommu> getListeAreteOrdiCommu(){
        return this.listeAreteOrdiCommu;
    }

    /**
     * Affiche la liste d'équipements
     * @return String
     */
    public String afficherListeEquipements(){
        String s = "";
        for(Equipement e : this.listeEquipements){
            s += e.getNom() + " , ";
        }
        return s;
    }

    /**
     * Remplit les listes d'arêtes
     */
    public void remplirListesAretes(){
        for(Equipement e : listeEquipements){
            if(e instanceof Commutateur){

                Iterator it = ((Commutateur) e).getRouteursVoisins().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry paire = (Map.Entry)it.next();
                    this.ajouterAreteEntreCommutateurs(new AreteCommutateurs((Commutateur)e,
                            (Commutateur)paire.getKey()));
                    it.remove(); // avoids a ConcurrentModificationException
                }

            }

            if(e instanceof Ordinateur){
                this.ajouterAreteOrdiCommu(new AreteOrdiCommu((Ordinateur)e, ((Ordinateur) e).getCommutateur()));
            }
        }

        // On supprime les arêtes en double
        for(int i=0; i<listeAreteCommutateurs.size(); i++){
            for(int j=0; j<listeAreteCommutateurs.size(); j++){
                if(
                        this.listeAreteCommutateurs.get(i).getExtremite1() == this.listeAreteCommutateurs.get(j).getExtremite2() &&
                                this.listeAreteCommutateurs.get(i).getExtremite2() == this.listeAreteCommutateurs.get(j).getExtremite1()

                ){
                    this.listeAreteCommutateurs.remove(j);
                }
            }
        }

    }
}
