/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk2021graphalgosv2;

/**
 *
 * @author Q1.07Delfederico
 */
public class Dijkstra {
    
    public class DijkstraKnoten{
        
        //Knoten im Graphen
        private GraphNode knoten;
        //Vorgängerknoten beim kürzesten Weg
        private DijkstraKnoten vorgaenger;
        //Distanz vom Startknoten
        private double distanz;
        
        public DijkstraKnoten(GraphNode pKnoten){
            knoten = pKnoten;
            vorgaenger = null;
            distanz = Integer.MAX_VALUE;
        }
        
        public DijkstraKnoten(GraphNode pKnoten, double pDistanz){
            knoten = pKnoten;
            vorgaenger = null;
            distanz = pDistanz;
        }

        //Man muss den Knoten erhalten können, um die Methoden der Klasse GraphNode nutzen zu können
        public GraphNode getKnoten() {
            return knoten;
        }
        //Man braucht keinen setter, da man den Knoten nicht ersetzen soll

        //Man braucht den Vorgängerknoten um den kürzesten Weg ermitteln zu können
        public DijkstraKnoten getVorgaenger() {
            return vorgaenger;
        }

        //Wenn man einen neuen Vorgänger hat, muss man diesen Setzen können
        public void setVorgaenger(DijkstraKnoten vorgaenger) {
            this.vorgaenger = vorgaenger;
        }

        //Man muss die Distanz erhalten können, um zu sehen ob die neue Distanz kleiner ist als die kleine
        public double getDistanz() {
            return distanz;
        }

        //Man muss die Distanz des Knotens aktuallisieren können
        public void setDistanz(double distanz) {
            this.distanz = distanz;
        }
        
    }
    
    public Dijkstra(){
        
    }
    
    public void resetMarks(Graph graph){
        if(graph.isEmpty()) return;
        List<GraphNode> nodes = graph.getVertices();
        nodes.toFirst();
        while(nodes.hasAccess()){
            nodes.getContent().setMark(false);
            nodes.next();
        }
    }
    
    public Stack<DijkstraKnoten> dijkstraAlgorithmus(Graph graph, GraphNode start, GraphNode end){
        //Falls die Parameter nicht existieren oder die Knoten nicht im Graphen enthalten sind, wird die Methode abgebrochen
        if(graph == null || start == null || end == null || !graph.hasNode(start.getID()) || !graph.hasNode(end.getID())) return null;
        //All eMarkierungen werden zurückgesetzt, um Fehler zu vermeiden
        resetMarks(graph);
        //Bis zeile 93 werden alle Knoten zu Dijkstraknoten gemacht und in die Liste "liste" getan
        List<GraphNode> nodes = graph.getVertices();
        List<DijkstraKnoten> liste = new List<>();
        nodes.toFirst();
        while(nodes.hasAccess()){
            //Wenn der Knoten der hinzugefügt wird der Startknoten ist, kriegt er die Distanz 0, ansonsten unendlich
            if(nodes.getContent() == start){
                liste.append(new DijkstraKnoten(nodes.getContent(), 0));
            } else{
            liste.append(new DijkstraKnoten(nodes.getContent()));
            }
            nodes.next();
        }
        //Wenn alle Knoten markiert sind (also man von jedem Knoten die Distanz ermittelt hat) wird die Schleife abgebrochen
        while (!graph.allVerticesMarked()){
            //Bis Zeile 108 wird der knoten mit der kleinsten Distanz, welcher noch nicht markiert ist (nocht nicht besucht wurde) rausgesucht
            liste.toFirst();
            while(liste.getContent().getKnoten().isMarked() && liste.hasAccess()){
                liste.next();
            }
            DijkstraKnoten kleinsteDistanz = liste.getContent();
            while(liste.hasAccess()){
                if((liste.getContent().getDistanz()<kleinsteDistanz.getDistanz() && !liste.getContent().getKnoten().isMarked())){
                    kleinsteDistanz=liste.getContent();
                }
                liste.next();
            }
            //System.out.println("Kleinster Knoten: " + kleinsteDistanz.getKnoten().getID() + "\n*-----------------*");
            //Alle Nachbarn vom aktuellen Knoten werden als DijkstraKnoten und der liste "nachbarn" gespeichert
            kleinsteDistanz.getKnoten().setMark(true);
            List<GraphNode> temp = graph.getNeighbours(kleinsteDistanz.getKnoten());
            List<DijkstraKnoten> nachbarn = new List<>();
            temp.toFirst();
            while(temp.hasAccess()){
                nachbarn.append(new DijkstraKnoten(temp.getContent()));
                temp.next();
            }
            //Die Distanz und der Vorgänger aller Nachbarn wird aktualisiert
            while(!nachbarn.isEmpty()){
                nachbarn.toFirst();
                //Der aktueller Nachbar wird zur Übersicht in der Variable "aktuellerNachbar" gespeichert
                liste.toFirst();
                while(liste.hasAccess() && liste.getContent().getKnoten() != nachbarn.getContent().getKnoten()){
                    liste.next();
                }
                DijkstraKnoten aktuellerNachbar = liste.getContent();
                //Wenn der aktuelle Knoten noch nicht markiert(noch nicht besucht) wurde
                if(!aktuellerNachbar.getKnoten().isMarked()){
                    //Die neue Distanz des aktuellen Knotens setzt sich aus der Distanz des aktuellen Knotens plus dem Gewicht der Kante zwischen ihm und dem aktuellen Knoten zusammen
                    double kleinsteDist = kleinsteDistanz.getDistanz();
                    double edgeWeight = graph.getEdge(kleinsteDistanz.getKnoten(), aktuellerNachbar.getKnoten()).getWeight();
                    double newDist = kleinsteDist + edgeWeight;
                    /*System.out.println("Aktueller Knoten:" + kleinsteDistanz.getKnoten().getID());
                    System.out.println("Seine Distanz: " + kleinsteDist);
                    System.out.println("Distanz von " + aktuellerNachbar.getKnoten().getID() + ": " + aktuellerNachbar.getDistanz());
                    System.out.println("Kantengewicht: " + edgeWeight);
                    System.out.println("Theoretisches Gewicht: " + newDist);*/
                    //Wenn die neue Distanz kleiner als die alte Distanz ist, soll die Distanz aktualisiert werden und der Vorgänger des aktuellen nachbarns ist der aktuelle Knoten
                    if(newDist < aktuellerNachbar.getDistanz()){
                        aktuellerNachbar.setDistanz(newDist);
                        aktuellerNachbar.setVorgaenger(kleinsteDistanz);
                    }
                    //System.out.println("Aktuallisiertes Gewicht von " + aktuellerNachbar.getKnoten().getID() + ": " + aktuellerNachbar.getDistanz() + "\n-----------------");
                }
                nachbarn.remove();
            }
        }
        //Der Knoten "end" wird gesucht und dessen Distanz wird ausgegeben
        /*liste.toFirst();
        while(liste.hasAccess()){
            if(liste.getContent().getKnoten() == end){
                return liste.getContent().getDistanz();
            }
            liste.next();
        }
        return -1;*/
        liste.toFirst();
        while(liste.getContent().getKnoten() != end && liste.hasAccess()){
            liste.next();
        }
        return pfadZurueckgeben(liste.getContent());
    }
    
    public void geheZu(DijkstraKnoten knoten, List<DijkstraKnoten> liste){
        liste.toFirst();
        while(liste.hasAccess() && liste.getContent().getKnoten() != knoten.getKnoten()){
            liste.next();
        }
    }
    
    public Stack pfadZurueckgeben(DijkstraKnoten ziel){
        Stack<DijkstraKnoten> stack = new Stack<>();
        DijkstraKnoten aktuellerKnoten = ziel;
        while(aktuellerKnoten != null){
            stack.push(aktuellerKnoten);
            aktuellerKnoten = aktuellerKnoten.getVorgaenger();
        }
        return stack;
    }
    
}
