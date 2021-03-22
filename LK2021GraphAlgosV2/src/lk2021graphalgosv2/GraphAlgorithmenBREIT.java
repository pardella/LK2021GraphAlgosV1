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
public class GraphAlgorithmenBREIT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphAlgorithmenBREIT g = new GraphAlgorithmenBREIT();
    }
    
    public GraphAlgorithmenBREIT()
    {
        Graph g = new Graph();        
        
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
        
        
        
       // breit(R1, g);
        
        if(breitsuch(R1, g, R8))
            System.out.println("JA");

        
    }
    
    
    /**
     * @param args GraphNode SK, Graph g
     * 
     **/
    public void breit (GraphNode SK, Graph g)
    {
        g.resetMarks();
        Queue<GraphNode> q = new Queue<>();     ///In der queue werden die abzuarebitenden Elemente gespeichert
        q.enqueue(SK);                          ///Startknoten wird sofort in die queue geschmissen und markiert
        SK.setMark(true);
 
       while(!q.isEmpty())
       {
           System.out.println(q.front().getID());   ///Es wird die ID des Knoten Ausgegeben
           
           if(!g.getNeighbours(q.front()).isEmpty())
           {
               List<GraphNode>  l = g.getNeighbours(q.front()); ///In der Liste werden alle Nachbarn des aktuellen Knoten gespeichert
               l.toFirst();
               while (l.hasAccess())                            /// Zugriff auf Liste = current ist gesetzt
               { 
                   if(!l.getContent().isMarked()) /// Wenn current der Liste nicht markiert ist:
                   {
                        q.enqueue(l.getContent());  /// Inhalt des current der Liste wird in Queue reingeschrieben
                        l.getContent().setMark(true); /// current der Liste wird markiert
                   }
                   l.next();                                /// Liste geht auf nächstes Objekt
               }
           }
           q.dequeue(); /// Queue schmeißt vorderstes Element aus
       }
    }
    
    
    
    public boolean breitsuch (GraphNode SK, Graph g, GraphNode SuchK)
    {
        g.resetMarks();
        Queue<GraphNode> q = new Queue<>();
        q.enqueue(SK);
        SK.setMark(true);
       
       while(!q.isEmpty())
       {
           if (q.front() == SuchK)
               return true;
           System.out.println(q.front().getID());
           
           if(!g.getNeighbours(q.front()).isEmpty())
           {
               List<GraphNode>  l = g.getNeighbours(q.front());
               l.toFirst();
               while (l.hasAccess())
               { 
                   if(!l.getContent().isMarked())
                   {
                        q.enqueue(l.getContent());
                        l.getContent().setMark(true);
                   }
                   l.next();
               }
           }
           q.dequeue();
       }
       return false;
    }
    
}
