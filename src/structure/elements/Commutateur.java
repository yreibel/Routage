package structure.elements;

import javafx.scene.control.Tab;
import structure.TableRoutage;
import structure.TopologieReseau;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe Commutateur
 * @author Yann REIBEL L3 INFO
 */
public class Commutateur extends Equipement {

    private static ImageIcon icone = new ImageIcon("");

    private Map<Commutateur, Integer> routeursVoisins;
    private TableRoutage tableRoutage;


    /**
     * Constructeur Commutateur
     * @param nom
     */
    public Commutateur(String nom, TopologieReseau topologieReseau){
        super(nom, topologieReseau);

        this.routeursVoisins = new HashMap<>();
        this.tableRoutage = new TableRoutage(this, super.topologieReseau);
    }

    /**
     * Retourne les routeurs voisins
     * @return HashMap
     */
    public Map getRouteursVoisins(){
        return this.routeursVoisins;
    }

    /**
     * Retourne le nom du commutateur
     * @return String
     */
    public String getNom(){
        return super.nom;
    }

    /**
     * Retourne la table de routage associée au Commutateur
     * @return TableRoutage
     */
    public TableRoutage getTableRoutage(){
        return this.tableRoutage;
    }

    /**
     * Ajoute un routeur voisin à la liste des routeurs
     * L'ajoute aussi dans l'instance de l'objet concerné
     * @param valeur
     * @param commutateur
     */
    public void ajouterRouteurVoisin(Integer valeur, Commutateur commutateur){
        this.routeursVoisins.put(commutateur, valeur);
        System.out.println("ajout" + this.routeursVoisins.isEmpty());
        commutateur.routeursVoisins.put(this, valeur);
    }

    /**
     * Retire un routeur voisin à la liste des routeurs
     * Le retire aussi dans l'instance de l'objet concerné
     * @param commutateur
     */
    public void retirerRouteurVoisin(Commutateur commutateur){
        commutateur.routeursVoisins.remove(this);
        this.routeursVoisins.remove(commutateur);

    }

    /**
     * Méthode toString
     * @return String
     */
    public String toString(){
        return super.nom;
    }

    public void afficherVoisins(){
        String s = "";

        for(Map.Entry<Commutateur,Integer> entry: this.routeursVoisins.entrySet()){
            System.out.println(entry.getKey().getNom()); //access method in key
        }
        //return s;
    }
}
