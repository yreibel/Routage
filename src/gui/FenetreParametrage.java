package gui;

import elements.TopologieReseau;
import test.AffichageGraph;

import javax.swing.*;
import java.awt.*;


/**
 * Classe FenetreParametrage permet de créer les ordinateurs et commutateurs à représenter
 * @author Yann REIBEL L3 INFO
 */
public class FenetreParametrage extends JFrame {

    private AffichageGraph affichageGraph;

    private TopologieReseau topologieReseau;

    private JPanel panelParametrage;
    private JPanel panelAppliquer;

    private PanelCreation panelCreation;
    private PanelListeVoisins panelListeVoisins;

    private JButton btnAppliquer;


    /**
     * Constructeur FenetreParametrage
     */
    public FenetreParametrage(){
        super("Fenêtre paramétrage");
        this.topologieReseau = new TopologieReseau();

        this.initialisationComposants();
        this.ajoutActions();

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initialisation des composants à la Fenêtre de Paramétrage
     * Autrement dit, initialisation du Panel de Paramétrage et pour appliquer les modifications effectuées
     */
    public void initialisationComposants(){
        this.initialisationPanelParametrage();
        this.initialisationPanelAppliquer();
        this.ajoutPanels();
    }

    /**
     * Initialisation du Panel de Paramétrage
     * Initialise le panel de création : PanelCreation et le PanelListeVoisins dans le PanelParametrage
     */
    public void initialisationPanelParametrage(){
        this.panelParametrage = new JPanel();
        this.panelCreation = new PanelCreation(this);
        this.panelListeVoisins = new PanelListeVoisins(this);

        this.panelParametrage.add(this.panelCreation);
        this.panelParametrage.add(this.panelListeVoisins);
    }

    /**
     * Initialisation du PanelAppliquer
     */
    public void initialisationPanelAppliquer(){
        this.panelAppliquer = new JPanel();
        this.btnAppliquer = new JButton("Appliquer");

        this.panelAppliquer.add(this.btnAppliquer);

    }

    /**
     * Ajoute le PanelParamétrage et le PanelAppliquer à la FenetreParametrage
     */
    public void ajoutPanels(){
        this.add(this.panelParametrage, BorderLayout.CENTER);
        this.add(this.panelAppliquer, BorderLayout.SOUTH);
    }

    /**
     * Implémente les actions sur les composants
     */
    public void ajoutActions(){
        this.btnAppliquer.addActionListener((e)->{
            this.dispose();
            this.affichageGraph = new AffichageGraph();
        });
    }

    /**
     * Retourne la topologie réseau utilisée
     * @return TopologieReseau
     */
    public TopologieReseau getTopologieReseau(){
        return this.topologieReseau;
    }

    /**
     * Main de lancement de le fenêtre de paramétrage
     * @param args
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(FenetreParametrage::new);
    }
}