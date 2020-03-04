package gui;

import structure.elements.Commutateur;
import structure.elements.Equipement;
import structure.elements.Ordinateur;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe PanelListeVoisins
 * @author Yann REIBEL L3 INFO
 */
public class PanelListeVoisins extends JPanel {

    private FenetreParametrage fenetreParametrage;

    private DefaultListModel<Equipement> defaultListModel;
    private JList<Equipement> jListVoisins;

    private SpinnerNumberModel spinnerListModel;
    private JSpinner jSpinnerChoixDistance;

    private JComboBox<Equipement> jComboBoxEquipements;

    private JButton btnAjouter;
    private JButton btnSupprimer;



    /**
     * Constructeur PanelListeVoisins
     * @param fenetreParametrage
     */
    public PanelListeVoisins(FenetreParametrage fenetreParametrage){
        this.fenetreParametrage = fenetreParametrage;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.initialisationComposants();
        this.ajoutActions();
    }

    /**
     * Initialisation des composants dans la fenêtre de Panel
     */
    public void initialisationComposants(){

        this.jComboBoxEquipements = new JComboBox<>();

        this.spinnerListModel = new SpinnerNumberModel(1,1,50,1);
        this.jSpinnerChoixDistance = new JSpinner(this.spinnerListModel);


        this.defaultListModel = new DefaultListModel<>();
        this.jListVoisins = new JList<>(this.defaultListModel);
        this.jListVoisins.setVisibleRowCount(10);

        this.btnAjouter = new JButton("Ajouter un Voisin");
        this.btnSupprimer = new JButton("Supprimer Voisin Sélectionné");


        // Ajout des composants au Panel
        this.add(this.jComboBoxEquipements);
        this.add(this.jSpinnerChoixDistance);
        this.add(this.btnAjouter);
        this.add(new JScrollPane(this.jListVoisins));
        this.add(this.btnSupprimer);

    }

    /**
     * Ajoute les actions aux composants du PanelListeVoisins
     */
    public void ajoutActions(){

        this.btnSupprimer.addActionListener((e)->{
            // Si
            Equipement selection = this.fenetreParametrage.getPanelCreation().getJListeEquipements().getSelectedValue();
            DefaultListModel modele = (DefaultListModel) this.jListVoisins.getModel();
            //int index = this.jListVoisins.getSelectedIndex();
            //if (index != -1) {
                Commutateur voisin = (Commutateur) this.jListVoisins.getSelectedValue();
                //modele.remove(index);
                modele.removeElement(this.jListVoisins.getSelectedValue());
                if(selection instanceof Commutateur){
                   // Commutateur voisin = (Commutateur) this.jListVoisins.getSelectedValue();
                    //debut test
                    ((Commutateur) selection).afficherVoisins();
                    //fin test
                    ((Commutateur) selection).retirerRouteurVoisin(voisin);
                    this.nettoyerJComboBoxEquipements();
                    this.remplirJComboBoxEquipements(selection);
                }
                if(selection instanceof Ordinateur){
                    //Commutateur voisin = (Commutateur) this.jListVoisins.getSelectedValue();
                    ((Ordinateur) selection).retirerCommutateur();
                    this.nettoyerJComboBoxEquipements();
                    this.remplirJComboBoxEquipements(selection);
                }
            //}
        });


        this.btnAjouter.addActionListener((e)->{
            Equipement equipementChoisi = fenetreParametrage.getPanelCreation().getJListeEquipements().getSelectedValue();
            Commutateur voisinAAJouter = (Commutateur) jComboBoxEquipements.getSelectedItem();
            int distance = (Integer)jSpinnerChoixDistance.getValue();

            if(equipementChoisi instanceof Commutateur){
                ((Commutateur) equipementChoisi).ajouterRouteurVoisin(distance, voisinAAJouter);
                defaultListModel.addElement(voisinAAJouter);
                //debut test
                ((Commutateur) equipementChoisi).afficherVoisins();
                //fin test
                this.nettoyerJComboBoxEquipements();
                this.remplirJComboBoxEquipements(equipementChoisi);

            }
            if(equipementChoisi instanceof Ordinateur){
                ((Ordinateur) equipementChoisi).associerCommutateur(voisinAAJouter);
                defaultListModel.addElement(voisinAAJouter);
                this.nettoyerJComboBoxEquipements();
                this.remplirJComboBoxEquipements(equipementChoisi);
            }
        });


    }

    /**
     * Retourne la JList représentant les voisins de l'item sélectionné dans la JList du PanelCreation
     * @return JList
     */
    public JList<Equipement> getjListVoisins(){
        return this.jListVoisins;
    }

    /**
     * Vide le modèle utilisé par la JList
     */
    public void nettoyerModeleJListVoisins(){
        this.defaultListModel.clear();
    }

    /**
     * Remplit le modèle associé à la JList représentant les voisins par les voisins réels de l'équipement passé
     * en paramètre de la méthode.
     * @param equipementChoisi
     */
    public void remplirModeleJListVoisins(Equipement equipementChoisi){
        if(equipementChoisi instanceof Commutateur){

            Iterator it = ((Commutateur) equipementChoisi).getRouteursVoisins().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry paire = (Map.Entry)it.next();
                // test
                this.defaultListModel.addElement((Commutateur) paire.getKey());
                //it.remove();
            }

        }
        if(equipementChoisi instanceof Ordinateur){
            this.defaultListModel.addElement(((Ordinateur) equipementChoisi).getCommutateur());
        }
    }

    /**
     * Retire tous les éléments du JComboBox
     */
    public void nettoyerJComboBoxEquipements(){
        this.jComboBoxEquipements.removeAllItems();
    }


    /**
     * Remplit le jComBox représentant les éléments ajoutables à la liste de voisins
     * @param equipement
     */
    public void remplirJComboBoxEquipements(Equipement equipement){
        ArrayList<Equipement> liste = this.fenetreParametrage.getTopologieReseau().getListeEquipements();
        if(equipement instanceof Ordinateur){
            if(((Ordinateur) equipement).getCommutateur() == null) {
                for(Equipement e : liste){
                    if(e instanceof Commutateur)
                        this.jComboBoxEquipements.addItem(e);
                }
            }
        }
        if(equipement instanceof Commutateur){
            if(((Commutateur) equipement).getRouteursVoisins().isEmpty()){
                for(Equipement e : liste){
                    if(e instanceof Commutateur && e != equipement)
                        this.jComboBoxEquipements.addItem(e);
                }
            }
            else{
                for(Equipement equipementListe : liste){
                     if(((Commutateur) equipement).getRouteursVoisins().get(equipementListe) == null){
                         if (equipementListe instanceof Commutateur && equipementListe != equipement) {
                             this.jComboBoxEquipements.addItem(equipementListe);
                         }
                     }
                }
            }
        }
    }

}
