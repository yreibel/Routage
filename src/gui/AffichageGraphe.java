package gui;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import structure.TopologieReseau;
import structure.elements.*;

import java.util.*;

/**
 * Classe AffichageGraphe
 * Utilise les listes de la topologie réseau pour générer un graphe Réseau
 * @author Yann REIBEL L3 INFO
 */
public class AffichageGraphe {

    private Graph g;
    private TopologieReseau reseau;

    /**
     * Constructeur AffichageGraphe
     * @param reseau
     */
    public AffichageGraphe(TopologieReseau reseau){
        this.reseau = reseau;
        this.g = this.graphique();

        Viewer viewer = this.g.display();
        //this.g.display(true);

        this.calculRoutage( this.g.getNode("C2"));
    }

    /**
     * Crée le graphique à partir de la topologie réseau
     * @return Graph
     */
    public Graph graphique(){
        Graph g = new SingleGraph("graph");

        //g.addAttribute("ui.style", "fill-color: blue;");
        //g.addAttribute("ui.stylesheet", "graph { fill-color: red; }");
        //g.addAttribute("ui.stylesheet");

        // Ajout des noeuds au graphe
        for(Equipement e : this.reseau.getListeEquipements()){
            if(e instanceof Commutateur){
                g.addNode(e.getNom()).addAttribute("type", "commutateur");
                g.getNode(e.getNom()).addAttribute("ui.style", "fill-mode: image-scaled;fill-image: url('images/routeur.png');");
            }
            else if(e instanceof Ordinateur){
                g.addNode(e.getNom()).addAttribute("type", "ordinateur");
                g.getNode(e.getNom()).addAttribute("ui.style", "fill-color: red;");
            }
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

    /**
     * Calcul de la table de routage pour un Noeud
     * @param noeud
     */
    public void calculRoutage(Node noeud){
        Dijkstra dijkstra =new Dijkstra(Dijkstra.Element.NODE, null, null);
        dijkstra.init(this.g);

        Map<Node, ArrayList<Node>>  tableRoutage = new HashMap<>();
        ArrayList<Node> routeursVoisins = new ArrayList<>();
        ArrayList<Node> commutateurs = new ArrayList<>();

        // Récupère tous les commutateurs du graphe
        for(Node n : this.g.getNodeSet()){
            if(n.getLabel("type").equals("commutateur")){
                commutateurs.add(n);
            }
        }


        // Récupère tous les voisins et les ajoute à la liste
        Iterator<Node> it = noeud.getNeighborNodeIterator();
        while(it.hasNext()){
            Node n=it.next();
            if(n.getLabel("type").equals("commutateur")){
                routeursVoisins.add(n);
            }
        }

        // Parcourt tous les commutateurs (routeurs) du graphe
        for(Node commutateur : commutateurs){

            // Trie la liste selon la distance du Noeud avec l'ensemble des commutateurs parcourus
            Collections.sort(routeursVoisins, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    dijkstra.setSource(n1);
                    dijkstra.compute();
                    Integer longueurCheminN1 = (int) dijkstra.getPathLength(commutateur);
                    dijkstra.setSource(n2);
                    dijkstra.compute();
                    Integer longueurCheminN2 = (int) dijkstra.getPathLength(commutateur);
                    return longueurCheminN1.compareTo(longueurCheminN2);
                }
            });

            // Range une ligne de table de routage dans la HashMap
            tableRoutage.put(commutateur, routeursVoisins);

        }

        // Calcul et affichage de la table de routage pour un noeud
        System.out.println(this.afficherTableRoutage(tableRoutage));

    }

    /**
     * Retourne un String représentant la table de routage d'un noeud
     * @param tableRoutage
     * @return String
     */
    public String afficherTableRoutage(Map<Node, ArrayList<Node>> tableRoutage){
        String s = "";
        for(Map.Entry<Node,ArrayList<Node>> entree: tableRoutage.entrySet()){
            s += "=> " + entree.getKey().getId() + " : ";
            for(Node n : entree.getValue()){
                s += n.getId() + " ";
            }
            s += "\n";
        }

        return s;
    }
}
