package elements;

import javax.swing.*;

/**
 * Classe Abstraite Equipement
 * Sert de modèle pour la fabrication de Commutateur et Ordinateur
 * @author Yann REIBEL L3 INFO
 */
public abstract class Equipement {

    protected String nom;
    protected static ImageIcon icone;

    /**
     * Constructeur Equipement
     * @param nom
     */
    protected Equipement(String nom){
        this.nom = nom;
    }

    /**
     * Retourne le nom de l'équipement
     * @return String
     */
    public String getNom(){
        return this.nom;
    }

}
