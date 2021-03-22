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
public class GraphAlgorithmenTIEF {
    private Graph g;
    
    public static void main(String[] args) {
        GraphAlgorithmenTIEF a = new GraphAlgorithmenTIEF();
        
    }
    
    public GraphAlgorithmenTIEF() {
        g = new Graph();       
        // Donke Jäsper <3
        GraphNode R1 = new GraphNode("r1");
        GraphNode R2 = new GraphNode("r2");
        GraphNode R3 = new GraphNode("r3");
        GraphNode R4 = new GraphNode("r4");
        GraphNode R5 = new GraphNode("r5");
        GraphNode R6 = new GraphNode("r6");
        GraphNode R7 = new GraphNode("r7");
        GraphNode R8 = new GraphNode("r8");
       
        Edge r1r2 = new Edge(R1, R2, 5);
        Edge r3r2 = new Edge(R3, R2, 6);
        Edge r3r4 = new Edge(R3, R4, 5);
        Edge r1r5 = new Edge(R1, R5, 9);
        Edge r3r6 = new Edge(R3, R6, 7);
        Edge r4r6 = new Edge(R4, R6, 8);
        Edge r5r6 = new Edge(R5, R6, 11);
        Edge r1r7 = new Edge(R1, R7, 22);
        Edge r2r7 = new Edge(R2, R7, 19);
        Edge r5r7 = new Edge(R5, R7, 14);
        Edge r4r8 = new Edge(R4, R8, 14);
        Edge r7r8 = new Edge(R7, R8, 16);
             
        g.addNode(R1);
        g.addNode(R2);
        g.addNode(R3);
        g.addNode(R4);
        g.addNode(R5);
        g.addNode(R6);
        g.addNode(R7);
        g.addNode(R8);
       
        g.addEdge(r1r2);
        g.addEdge(r3r2);
        g.addEdge(r3r4);
        g.addEdge(r1r5);
        g.addEdge(r3r6);
        g.addEdge(r4r6);
        g.addEdge(r5r6);
        g.addEdge(r1r7);
        g.addEdge(r2r7);
        g.addEdge(r5r7);
        g.addEdge(r4r8);
        g.addEdge(r7r8);
        // Alle Knoten werden ausgegeben
        tiefensucheAberAlles(R1);
        g.resetMarks();
        System.out.println("---");
        // Man sucht nach Knoten r7 und gibt den dazugehörigen Pfad aus
        Stack<GraphNode> s = new Stack<>();
        System.out.println(tiefensuche(R1, "r7", s));
    }
    
    public GraphNode tiefensuche(GraphNode node, String goal, Stack<GraphNode> s) {
        if(node.getID().equals(goal)) {
            // Stack, der den Pfad enthält, wird ausgegeben
            while(!s.isEmpty()) {
                System.out.println(s.top());
                s.pop();
            }
            // Gesuchter Knoten wird zurückgegeben
            return node;
        }
        
        // Aktueller Knoten wird markiert
        node.setMark(true);
        s.push(node);
        // Nachbarn werden gespeichert
        List<GraphNode> l = g.getNeighbours(node);
        l.toFirst();
        
        // tiefensuche auf ersten nicht-markierten Element ausführen
        while(l.hasAccess()) {
            if(l.getContent().isMarked()) {
                l.next();
            } else {
                return tiefensuche(l.getContent(), goal, s);
            }
        }
        
        // Backtracking -> Befinden wir uns in einer Sackgasse?
        s.pop();
        if(!s.isEmpty()) {
            return tiefensuche(s.top(), goal, s);
        } else {
            return null;
        }

    }   
    
    public void tiefensucheAberAlles(GraphNode node) {
        // Sind schon alle Knoten ausgegeben?
        if(g.allVerticesMarked()) return;
        // Aktuelles Element wird ausgegeben
        System.out.println(node.getID());
        Stack<GraphNode> s = new Stack<>();
        // Aktuelles Element wird markiert
        node.setMark(true);
        s.push(node);
        
        // Nachbarn werden in Liste gespeichert
        List<GraphNode> l = g.getNeighbours(node);
        l.toFirst();
        
        // tiefensucheAberAlles wird auf erstem, nicht-markiertem, Nachbar ausgeführt
        while(l.hasAccess()) {
            if(l.getContent().isMarked()) {
                l.next();
            } else {
                tiefensucheAberAlles(l.getContent());
            }
        }
        
        // Backtracking -> Befinden wir uns in einer Sackgasse?
        s.pop();
        if(!s.isEmpty()) {
            tiefensucheAberAlles(s.top());
        } else {
            return;
        }

    }
    
}
