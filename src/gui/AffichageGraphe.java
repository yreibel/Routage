package gui;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import structure.TopologieReseau;
import structure.elements.AreteCommutateurs;
import structure.elements.AreteOrdiCommu;
import structure.elements.Equipement;

/**
 * Classe AffichageGraphe
 * Utilise les listes de la topologie réseau pour générer un graphe Réseau
 * @author Yann REIBEL L3 INFO
 */
public class AffichageGraphe {

    private TopologieReseau reseau;

    /**
     * Constructeur AffichageGraphe
     * @param reseau
     */
    public AffichageGraphe(TopologieReseau reseau){
        this.reseau = reseau;
        Graph g = this.graphique();
        g.display(true);
    }

    /**
     * Crée le graphique à partir de la topologie réseau
     * @return Graph
     */
    public Graph graphique(){
        Graph g = new SingleGraph("graph");

        // Ajout des noeuds au graphe
        for(Equipement e : this.reseau.getListeEquipements()){
            g.addNode(e.getNom());
        }

        // Dessine les arêtes entre les commutateurs
        for(AreteCommutateurs arete : this.reseau.getListeAreteCommutateurs()){
            String arete1 = arete.getExtremite1().getNom();
            String arete2 = arete.getExtremite2().getNom();

            g.addEdge(arete1+ ""+ arete2, arete1, arete2)
                    .addAttribute("length", arete.getDistance());
        }

        // Dessine les arêtes entre les ordinateurs et commutateurs
        for(AreteOrdiCommu areteOrdiCommu : this.reseau.getListeAreteOrdiCommu()){
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
}
