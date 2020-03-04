package structure.elements;

import structure.TopologieReseau;

import javax.swing.*;

/**
 * Classe Abstraite Equipement
 * Sert de modèle pour la fabrication de Commutateur et Ordinateur
 * @author Yann REIBEL L3 INFO
 */
public abstract class Equipement {

    protected String nom;
    protected TopologieReseau topologieReseau;

    /**
     * Constructeur Equipement
     * @param nom
     */
    protected Equipement(String nom, TopologieReseau topologieReseau){
        this.nom = nom;
        this.topologieReseau = topologieReseau;
    }

    /**
     * Retourne le nom de l'équipement
     * @return String
     */
    public String getNom(){
        return this.nom;
    }

}
