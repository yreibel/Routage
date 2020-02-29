package gui;

import elements.Commutateur;
import elements.Equipement;
import elements.Ordinateur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Classe PanelCreation
 * @author Yann REIBEL L3 INFO
 */
public class PanelCreation extends JPanel {

    private FenetreParametrage fenetreParametrage;

    private JPanel panelAjout;
    private JPanel panelListeEquipements;

    private JComboBox<String> choixEquipement;
    private JTextField nomEquipement;
    private JButton btnAjouter;

    private JList<Equipement> jListeEquipements;
    private DefaultListModel defaultListModel;

    private JButton btnSupprimer;

    /**
     * Constructeur PanelCreation
     * @param fenetreParametrage
     */
    public PanelCreation(FenetreParametrage fenetreParametrage){
        this.fenetreParametrage = fenetreParametrage;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.initialisationComposants();
        this.ajoutActions();
    }

    /**
     * Initialisation des composants
     */
    public void initialisationComposants(){
        this.initialisationPanelAjout();
        this.initialisationPanelListeEquipements();
        this.ajoutPanels();
    }

    /**
     *
     */
    public void initialisationPanelAjout(){
        this.panelAjout = new JPanel();
        //this.panelAjout.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.panelAjout.setLayout(new BorderLayout());

        this.choixEquipement = new JComboBox<>();
        this.choixEquipement.addItem("Ordinateur");
        this.choixEquipement.addItem("Commutateur");

        this.nomEquipement = new JTextField();

        this.btnAjouter = new JButton("Ajouter");

        // Ajouts au panel
        this.panelAjout.add(this.choixEquipement, BorderLayout.NORTH);
        this.panelAjout.add(this.nomEquipement, BorderLayout.CENTER);
        this.panelAjout.add(this.btnAjouter, BorderLayout.SOUTH);




    }

    /**
     *
     */
    public void initialisationPanelListeEquipements(){
        this.panelListeEquipements = new JPanel(new BorderLayout());
        this.defaultListModel = new DefaultListModel();

        this.jListeEquipements = new JList();
        this.jListeEquipements.setModel(this.defaultListModel);
        this.jListeEquipements.setVisibleRowCount(6);
        this.jListeEquipements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.btnSupprimer = new JButton("Supprimer Equipement");

        // Ajouts au Panel
        this.panelListeEquipements.add(new JScrollPane(this.jListeEquipements), BorderLayout.NORTH);
        this.panelListeEquipements.add(this.btnSupprimer, BorderLayout.SOUTH);

    }

    /**
     *
     */
    public void ajoutPanels(){
        this.add(this.panelAjout);
        this.add(this.panelListeEquipements);
    }

    /**
     *
     */
    public void ajoutActions(){
        this.btnSupprimer.addActionListener((e)->{
            DefaultListModel modele = (DefaultListModel) this.jListeEquipements.getModel();
            int index = this.jListeEquipements.getSelectedIndex();
            if (index != -1) {
                modele.remove(index);
                this.fenetreParametrage.getTopologieReseau().retirerEquipement(this.jListeEquipements.getSelectedValue());
            }
        });

        this.btnAjouter.addActionListener((e)->{
           String nomEquipement =  (String) this.choixEquipement.getSelectedItem();
           String nomChoisi = this.nomEquipement.getText();
           if(nomEquipement.equals("Ordinateur")){
                Ordinateur ordinateur = new Ordinateur(nomChoisi);
                this.fenetreParametrage.getTopologieReseau().ajouterEquipement(ordinateur);
                this.defaultListModel.addElement(ordinateur);
           }

          if(nomEquipement.equals("Commutateur")){
               Commutateur commutateur = new Commutateur(nomChoisi);
               this.fenetreParametrage.getTopologieReseau().ajouterEquipement(commutateur);
               this.defaultListModel.addElement(commutateur);
           }
        });
    }

}
