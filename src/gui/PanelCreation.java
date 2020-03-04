package gui;

import structure.elements.Commutateur;
import structure.elements.Equipement;
import structure.elements.Ordinateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;

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
    private DefaultListModel<Equipement> defaultListModel;

    private JButton btnSupprimer;

    private JLabel labelNom;

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
     * Initialise du PanelAjout permettant de créer de nouveaux équipements(équipements, commutateurs).
     */
    public void initialisationPanelAjout(){
        this.panelAjout = new JPanel();
        this.panelAjout.setLayout(new BorderLayout());

        this.choixEquipement = new JComboBox<>();
        this.choixEquipement.addItem("Ordinateur");
        this.choixEquipement.addItem("Commutateur");

        this.labelNom = new JLabel("Nom Equipement  : ");

        this.nomEquipement = new JTextField();

        this.btnAjouter = new JButton("Ajouter");

        // Ajouts au panel
        this.panelAjout.add(this.choixEquipement, BorderLayout.NORTH);
        this.panelAjout.add(this.labelNom, BorderLayout.WEST);
        this.panelAjout.add(this.nomEquipement, BorderLayout.CENTER);
        this.panelAjout.add(this.btnAjouter, BorderLayout.SOUTH);




    }

    /**
     * Initialise du PanelListeEquipements permettant
     */
    public void initialisationPanelListeEquipements(){
        this.panelListeEquipements = new JPanel(new BorderLayout());
        this.defaultListModel = new DefaultListModel<>();

        this.jListeEquipements = new JList(this.defaultListModel);
        this.jListeEquipements.setVisibleRowCount(10);
        this.jListeEquipements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.btnSupprimer = new JButton("Supprimer Equipement");

        // Ajouts au Panel
        this.panelListeEquipements.add(new JScrollPane(this.jListeEquipements), BorderLayout.NORTH);
        this.panelListeEquipements.add(this.btnSupprimer, BorderLayout.SOUTH);

    }

    /**
     * Ajoute le panelAjout et le panelListeEquipements au PanelCreation
     */
    public void ajoutPanels(){
        this.add(this.panelAjout);
        this.add(this.panelListeEquipements);
    }

    /**
     * Ajoute les actions aux composants du PanelCreation
     */
    public void ajoutActions(){
        this.btnSupprimer.addActionListener((e)->{
            DefaultListModel<Equipement> modele = (DefaultListModel<Equipement>) this.jListeEquipements.getModel();
            int index = this.jListeEquipements.getSelectedIndex();
            Equipement selectionSuppression = this.jListeEquipements.getSelectedValue();

            // Si l'élément se trouve dans un arraylist de ses voisins, cela le supprime
            if(selectionSuppression instanceof Commutateur){
                Iterator it =  ((Commutateur) selectionSuppression).getRouteursVoisins().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry voisin = (Map.Entry)it.next();

                    if(((Commutateur) voisin.getKey()).getRouteursVoisins().get(selectionSuppression) != null ){
                       ((Commutateur) voisin.getKey()).retirerRouteurVoisin((Commutateur) selectionSuppression);
                    }
                }
            }

            // Supprime l'élément de la topologie réseau
            this.fenetreParametrage.getTopologieReseau().retirerEquipement(selectionSuppression);
            if (index != -1) {
                modele.remove(index);
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

        this.jListeEquipements.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    Equipement selection = (Equipement) jListeEquipements.getSelectedValue();

                    fenetreParametrage.getPanelListeVoisins().nettoyerModeleJListVoisins();
                    fenetreParametrage.getPanelListeVoisins().remplirModeleJListVoisins(selection);

                    fenetreParametrage.getPanelListeVoisins().nettoyerJComboBoxEquipements();
                    fenetreParametrage.getPanelListeVoisins().remplirJComboBoxEquipements(selection);

                    //test
                    /*if(selection instanceof Commutateur)
                       // ((Commutateur) selection).afficherVoisins();
                    System.out.println(((Commutateur) selection).getRouteursVoisins().isEmpty());
                    if(selection instanceof Ordinateur)
                        System.out.println(((Ordinateur) selection).getCommutateur().getNom());
                    */

                    // fin test

                }
            }
        });
    }


    /**
     * Retourne le composant JList
     * @return JList
     */
    public JList<Equipement> getJListeEquipements() {
        return jListeEquipements;
    }

    /**
     * Retourne la fenêtre de paramétrage
     * @return FenetreParametrage
     */
    public FenetreParametrage getFenetreParametrage(){
        return this.fenetreParametrage;
    }
}
