/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk2021graphalgosv2;

/**
 *
 * @author pardella
 */
public class GraphAlgorithmenDijkstra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph buero = new Graph();
        GraphNode eins = new GraphNode("1");
        GraphNode zwei = new GraphNode("2");
        GraphNode drei = new GraphNode("3");
        GraphNode vier = new GraphNode("4");
        GraphNode fuenf = new GraphNode("5");
        GraphNode sechs = new GraphNode("6");
        GraphNode sieben = new GraphNode("7");
        GraphNode acht = new GraphNode("8");
        buero.addNode(eins);
        buero.addNode(zwei);
        buero.addNode(drei);
        buero.addNode(vier);
        buero.addNode(fuenf);
        buero.addNode(sechs);
        buero.addNode(sieben);
        buero.addNode(acht);
        buero.addEdge(new Edge(eins, zwei, 5));
        buero.addEdge(new Edge(zwei, eins, 5));
        buero.addEdge(new Edge(eins, fuenf, 9));
        buero.addEdge(new Edge(fuenf, eins, 9));
        buero.addEdge(new Edge(eins, sieben, 22));
        buero.addEdge(new Edge(sieben, eins, 22));
        buero.addEdge(new Edge(zwei, drei, 6));
        buero.addEdge(new Edge(drei, zwei, 6));
        buero.addEdge(new Edge(zwei, sieben, 19));
        buero.addEdge(new Edge(sieben, zwei, 19));
        buero.addEdge(new Edge(drei, vier, 5));
        buero.addEdge(new Edge(vier, drei, 5));
        buero.addEdge(new Edge(drei, sechs, 7));
        buero.addEdge(new Edge(sechs, drei, 7));
        buero.addEdge(new Edge(vier, sechs, 8));
        buero.addEdge(new Edge(sechs, vier, 8));
        buero.addEdge(new Edge(vier, acht, 14));
        buero.addEdge(new Edge(acht, vier, 14));
        buero.addEdge(new Edge(fuenf, sechs, 11));
        buero.addEdge(new Edge(sechs, fuenf, 11));
        buero.addEdge(new Edge(fuenf, sieben, 14));
        buero.addEdge(new Edge(sieben, fuenf, 14));
        buero.addEdge(new Edge(sieben, acht, 16));
        buero.addEdge(new Edge(acht, sieben, 16));
        
        Dijkstra d = new Dijkstra();
        
        //System.out.println(d.dijkstraAlgorithmus(buero, eins, drei));
        System.out.println("Der Pfad von eins bis drei lautet:");
        Stack<Dijkstra.DijkstraKnoten> pfad = d.dijkstraAlgorithmus(buero, eins, drei);
        while(true){
            System.out.println(pfad.top().getKnoten().getID());
            pfad.pop();
            if(!pfad.isEmpty())System.out.println("↓");
            else break;
        }
    }
    
}
