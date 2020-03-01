package gui;

import elements.AreteCommutateurs;
import elements.Commutateur;
import elements.Equipement;
import elements.Ordinateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe PanelListeVoisins
 * @author Yann REIBEL L3 INFO
 */
public class PanelListeVoisins extends JPanel {

    private FenetreParametrage fenetreParametrage;

    private DefaultListModel defaultListModel;
    private JList<Equipement> jListVoisins;

    private SpinnerListModel spinnerListModel;
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

        this.spinnerListModel = new SpinnerListModel();
        this.jSpinnerChoixDistance = new JSpinner();
        this.jSpinnerChoixDistance.setModel(this.spinnerListModel);


        this.defaultListModel = new DefaultListModel();
        this.jListVoisins = new JList<>();
        this.jListVoisins.setModel(this.defaultListModel);

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
            int index = this.jListVoisins.getSelectedIndex();
            if (index != -1) {
                modele.remove(index);
                if(selection instanceof Commutateur){
                    Commutateur voisin = (Commutateur) this.jListVoisins.getSelectedValue();
                    ((Commutateur) selection).retirerRouteurVoisin(voisin);
                }
                if(selection instanceof Ordinateur){
                    Commutateur voisin = (Commutateur) this.jListVoisins.getSelectedValue();
                    ((Ordinateur) selection).retirerCommutateur();
                }
            }
        });

        this.jComboBoxEquipements.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(jComboBoxEquipements.getSelectedIndex() > 0){
                    Equipement equipementChoisi = fenetreParametrage.getPanelCreation().getJListeEquipements().getSelectedValue();
                    Equipement voisinAAJouter = (Equipement)jComboBoxEquipements.getSelectedItem();

                    if(equipementChoisi instanceof Commutateur){
                        //equipementChoisi.a
                    }
                    if(equipementChoisi instanceof Ordinateur){

                    }

                }
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
    public void nettoyerModele(){
        this.defaultListModel.clear();
    }

    /**
     * Remplit le modèle associé à la JList représentant les voisins par les voisins réels de l'équipement passé
     * en paramètre de la méthode.
     * @param equipementChoisi
     */
    public void remplirModele(Equipement equipementChoisi){
        if(equipementChoisi instanceof Commutateur){

            Iterator it = ((Commutateur) equipementChoisi).getRouteursVoisins().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry paire = (Map.Entry)it.next();
                this.defaultListModel.addElement(paire);
                it.remove(); // avoids a ConcurrentModificationException
            }

        }
        if(equipementChoisi instanceof Ordinateur){
            this.defaultListModel.addElement(((Ordinateur) equipementChoisi).getCommutateur());
        }
    }



}
