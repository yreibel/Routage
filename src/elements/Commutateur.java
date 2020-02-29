package elements;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Commutateur
 * @author Yann REIBEL L3 INFO
 */
public class Commutateur extends Equipement {

    private static ImageIcon icone = new ImageIcon("");

    private Map<Commutateur, Integer> routeursVoisins;

    /**
     * Constructeur Commutateur
     * @param nom
     */
    public Commutateur(String nom){
        super(nom);
        this.routeursVoisins = new HashMap<>();
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
     * Ajouter un routeur voisin à la liste des routeurs
     * @param valeur
     * @param commutateur
     */
    public void ajouterRouteurVoisin(Integer valeur, Commutateur commutateur){
        this.routeursVoisins.put(commutateur, valeur);
        commutateur.routeursVoisins.put(this, valeur);
    }

    public String toString(){
        return super.nom;
    }
}
