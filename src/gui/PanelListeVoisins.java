package gui;

import elements.Equipement;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe PanelListeVoisins
 * @author Yann REIBEL L3 INFO
 */
public class PanelListeVoisins extends JPanel {

    private FenetreParametrage fenetreParametrage;

    private DefaultListModel defaultListModel;
    private JList<Equipement> jListVoisins;

    private JComboBox<Equipement> jComboBoxEquipements;

    private JButton btnSupprimer;

    /**
     * Constructeur PanelListeVoisins
     * @param fenetreParametrage
     */
    public PanelListeVoisins(FenetreParametrage fenetreParametrage){
        this.fenetreParametrage = fenetreParametrage;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.initialisationComposants();
        //this.ajoutActions();
    }

    /**
     * Initialisation des composants dans la fenêtre de Panel
     */
    public void initialisationComposants(){

        this.jComboBoxEquipements = new JComboBox<>();
        this.defaultListModel = new DefaultListModel();

        this.jListVoisins = new JList<>();
        this.jListVoisins.setModel(this.defaultListModel);

        this.btnSupprimer = new JButton("Supprimer");


        // Ajout des composants au Panel
        this.add(this.jComboBoxEquipements);
        this.add(new JScrollPane(this.jListVoisins));
        this.add(this.btnSupprimer);

    }

    /**
     *
     */
   /* public void ajoutActions(){
        this.btnSupprimer.addActionListener((e)->{
            DefaultListModel modele = (DefaultListModel) this.jListVoisins.getModel();
            int index = this.jListVoisins.getSelectedIndex();
            if (index != -1) {
                modele.remove(index);
                this.jListVoisins.getSelectedValue().
            }
        });
    }*/

}
