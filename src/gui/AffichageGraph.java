package gui;

import elements.*;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe AffichageGraph
 * @author Yann REIBEL L3 INFO
 */
public class AffichageGraph {

    private ArrayList<Equipement> listeEquipements;
    private ArrayList<AreteCommutateurs> listeAretesCommutateurs;
    private ArrayList<AreteOrdiCommu> listeAretesOrdiCommu;

    /**
     * Constructeur AffichageGraph
     */
    public AffichageGraph(){
        Graph g = graphique();
        g.display(true);
    }

    /**
     *
     * @return Graph
     */
    public Graph graphique(){
        this.simulationCreationEquipements();

        Graph g = new SingleGraph("graph");

        // Ajout des noeuds au graphe
        for(Equipement e : listeEquipements){
            g.addNode(e.getNom());
        }

        // On boucle à travers les équipements pour définir les arêtes nécessaires
        for(Equipement e : listeEquipements){
            if(e instanceof Commutateur){

                Iterator it = ((Commutateur) e).getRouteursVoisins().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry paire = (Map.Entry)it.next();
                    this.listeAretesCommutateurs.add(new AreteCommutateurs((Commutateur)e,
                            (Commutateur)paire.getKey()));
                    it.remove(); // avoids a ConcurrentModificationException
                }

            }

            if(e instanceof Ordinateur){
                this.listeAretesOrdiCommu.add(new AreteOrdiCommu((Ordinateur)e, ((Ordinateur) e).getCommutateur()));
            }
        }

        // On supprime les arêtes en double
        for(int i=0; i<listeAretesCommutateurs.size(); i++){
            for(int j=0; j<listeAretesCommutateurs.size(); j++){
                if(
                        this.listeAretesCommutateurs.get(i).getExtremite1() == this.listeAretesCommutateurs.get(j).getExtremite2() &&
                        this.listeAretesCommutateurs.get(i).getExtremite2() == this.listeAretesCommutateurs.get(j).getExtremite1()

                ){
                    this.listeAretesCommutateurs.remove(j);
                }
            }
        }

        // Dessine les arêtes entre les commutateurs
        for(AreteCommutateurs arete : this.listeAretesCommutateurs){
            String arete1 = arete.getExtremite1().getNom();
            String arete2 = arete.getExtremite2().getNom();

            g.addEdge(arete1+ ""+ arete2, arete1, arete2)
                    .addAttribute("length", 1);
        }

        // Dessine les arêtes entre les ordinateurs et commutateurs
        for(AreteOrdiCommu areteOrdiCommu : this.listeAretesOrdiCommu){
            String arete1 = areteOrdiCommu.getExtremite1().getNom();
            String arete2 = areteOrdiCommu.getExtremite2().getNom();

            g.addEdge(arete1+ ""+ arete2, arete1, arete2);

        }


        // Ajout des labels aux noeuds et arêtes

        for (Node n : g)
            n.addAttribute("label", n.getId());

        for (Edge e : g.getEachEdge())
            e.addAttribute("label", "" + (int) e.getNumber("length"));

        return g;
    }

    /**
     * Simulation de créations d'ordinateurs et routeurs
     */
    public void simulationCreationEquipements(){

        this.listeEquipements = new ArrayList<>();
        this.listeAretesCommutateurs = new ArrayList<>();
        this.listeAretesOrdiCommu = new ArrayList<>();

        Ordinateur ordinateur1 = new Ordinateur("O1");
        Ordinateur ordinateur2 = new Ordinateur("O2");
        Ordinateur ordinateur3 = new Ordinateur("03");

        Commutateur commutateur1 = new Commutateur("C1");
        Commutateur commutateur2 = new Commutateur("C2");
        Commutateur commutateur3 = new Commutateur("C3");
        Commutateur commutateur4 = new Commutateur("C4");


        /*ordinateur1.associerCommutateur(commutateur1);
        ordinateur2.associerCommutateur(commutateur2);
        ordinateur3.associerCommutateur(commutateur3);*/

        ordinateur1.associerCommutateur(commutateur1);
        ordinateur2.associerCommutateur(commutateur2);
        ordinateur3.associerCommutateur(commutateur3);

        commutateur1.ajouterRouteurVoisin(1, commutateur2);

        commutateur2.ajouterRouteurVoisin(4, commutateur3);
        commutateur2.ajouterRouteurVoisin(1, commutateur4);

        commutateur3.ajouterRouteurVoisin(2, commutateur4);


        listeEquipements.add(ordinateur1);
        listeEquipements.add(ordinateur2);
        listeEquipements.add(ordinateur3);
        listeEquipements.add(commutateur1);
        listeEquipements.add(commutateur2);
        listeEquipements.add(commutateur3);
        listeEquipements.add(commutateur4);


    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args){
        AffichageGraph aff = new AffichageGraph();

    }
}
