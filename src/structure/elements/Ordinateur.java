package structure.elements;

import structure.TopologieReseau;

import javax.swing.*;

/**
 * Classe Ordinateur
 * @author Yann REIBEL L3 INFO
 */
public class Ordinateur extends Equipement{

    private Commutateur commutateur;

    /**
     * Constructeur Ordinateur
     * @param commutateur
     */
    public Ordinateur(String nom, Commutateur commutateur, TopologieReseau topologieReseau){
        super(nom, topologieReseau);
        this.commutateur = commutateur;
    }

    /**
     * Constructeur Ordinateur
     * @param nom
     */
    public Ordinateur(String nom, TopologieReseau topologieReseau){
        super(nom, topologieReseau);
    }

    /**
     * Retourne le commutateur associé
     * @return Commutateur
     */
    public Commutateur getCommutateur(){
        return this.commutateur;
    }

    /**
     * Retourne le nom de l'ordinateur
     * @return String
     */
    public String getNom(){
        return super.nom;
    }

    /**
     * Associe l'ordinateur à un commutateur
     * @param commutateur
     */
    public void associerCommutateur(Commutateur commutateur){
        this.commutateur = commutateur;
    }

    /**
     * Retire le commutateur associé à l'ordinateur
     */
    public void retirerCommutateur(){
        this.commutateur = null;
    }

    /**
     * Méthode toString
     * @return String
     */
    public String toString(){
        return super.nom;
    }

}
