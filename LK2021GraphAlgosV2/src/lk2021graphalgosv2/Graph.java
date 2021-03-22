package graphalgorithmen; 

/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 Klasse GGraph
 </p>
 * <p>
 Die Klasse GGraph stellt einen ungerichteten, kantengewichteten Graphen dar. Es koennen 
 Knoten- und Kantenobjekte hinzugefuegt und entfernt, flache Kopien der Knoten- und Kantenlisten 
 des Graphen angefragt und Markierungen von Knoten und Kanten gesetzt und ueberprueft werden.
 Des Weiteren kann eine Liste der Nachbarn eines bestimmten Knoten, eine Liste der inzidenten 
 Kanten eines bestimmten Knoten und die Kante von einem bestimmten Knoten zu einem 
 anderen bestimmten Knoten angefragt werden. Abgesehen davon kann abgefragt werden, welches 
 Knotenobjekt zu einer bestimmten ID gehoert und ob der GGraph leer ist.
 </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Oktober 2015
 * 
 * PARDELLA: Durch auskommentieren in den Zeilen 247ff und 298f aus dem unerichteten 
 * einen gerichteten graph zur Verwendung als SPSESGRaph gemacht. 2018-02-14
 */
public class Graph{
  private List<GraphNode> vertices;
  private List<Edge> edges;

  /**
   * Ein Objekt vom Typ Graph wird erstellt. Der von diesem Objekt 
   * repraesentierte Graph ist leer.
   */
  public Graph(){
    //Leere Listen fuer Knoten und Kanten erstellen.
    vertices = new List<GraphNode>();
    edges = new List<Edge>();
  }
  
   /**
   * Die Anfrage liefert true, wenn der GGraph keine Knoten enthaelt, ansonsten false.
   */
  public boolean isEmpty(){
    return vertices.isEmpty();
  } 

  /**
   * Der Auftrag fuegt den Knoten pNode in den Graphen ein, sofern es noch keinen
   * Knoten mit demselben ID-Eintrag wie pNode im Graphen gibt und pNode eine ID ungleich null hat. 
   * Ansonsten passiert nichts.
   * @param pNode: Der hinzuzufügende Knoten
   * -->addNode!
   */
  public void addNode(GraphNode pNode){
    //Pruefen, ob der GraphNode existiert und eine ID hat.
    if (pNode != null && pNode.getID() != null) {
      //Pruefen, ob nicht schon ein GraphNode mit gleicher ID enthalten ist.
      boolean freeID = true;
      vertices.toFirst();
      while (vertices.hasAccess() && freeID){
        if (vertices.getContent().getID().equals(pNode.getID())){
          freeID = false;
        }
        vertices.next();
      }

      //Wenn die ID noch frei ist, den GraphNode einfuegen, sonst nichts tun.
      if (freeID) {
        vertices.append(pNode);      
      }
    }
  }
  
  /**
   * Die Anfrage gibt true zurück, falls der Graph einen Knoten mit ID pName hat. 
   * Ansonsten liefert sie false zurück.
   * @param pName: Der zu suchende Name (ID)
   * @return true, falls Knoten mit ID pName existiert, false sonst
   */
  public boolean hasNode(String pName){
    if (pName != null ) {
      vertices.toFirst();
      while (vertices.hasAccess()){
        if (vertices.getContent().getID().equals(pName)){
          return true;
        }
        vertices.next();
      }
      return false;
    }
    return false;
  }
  
  /**
   * Die Anfrage liefert das Knotenobjekt mit pName als ID. Ist ein solchen Knotenobjekt nicht im Graphen enthalten,
   * wird null zurueckgeliefert.
   * @param pName gesuchter Knoten
   * @return Der gesuchte Knoten, falls er existiert, sonst null
   */
  public GraphNode getNode(String pName){
    //Vertex-Objekt mit pName als ID suchen.
    GraphNode result = null;
    vertices.toFirst();
    while (vertices.hasAccess() && result == null){
      if (vertices.getContent().getID().equals(pName)){
        result = vertices.getContent();
      }
      vertices.next();
    }

    //Objekt zurueckliefern.
    return result;
  }

  /**
   * Der Auftrag entfernt den Knoten pNode aus dem Graphen und loescht alle Kanten, die mit ihm inzident sind.
   * Ist der Knoten pNode nicht im Graphen enthalten, passiert nichts.
   * @param pNode Der zu löschende Knoten
   */
  public void removeNode(GraphNode pNode){
    //Inzidente Kanten entfernen.
    edges.toFirst();
    while (edges.hasAccess()){
      GraphNode[] akt = edges.getContent().getVertices();
      if (akt[0] == pNode || akt[1] == pNode){
        edges.remove();
      } else {
        edges.next();
      }
    }

    //Knoten entfernen
    vertices.toFirst();
    while (vertices.hasAccess() && vertices.getContent()!= pNode){
      vertices.next();
    }
    if (vertices.hasAccess()){
      vertices.remove();
    }
  }

  /**
   * Der Auftrag fuegt die Kante pEdge in den Graphen ein, sofern beide durch die Kante verbundenen Knoten
   * im Graphen enthalten sind, nicht identisch sind und noch keine Kante zwischen den Knoten existiert. Ansonsten passiert nichts.
   * 
   */
  public void addEdge(Edge pEdge){ 
    //Pruefen, ob pEdge exisitert.
    if (pEdge != null){  
      GraphNode[] vertexPair = pEdge.getVertices();
      
      //Einfuegekriterien pruefen.
      if (vertexPair[0] != null && vertexPair[1] != null && 
      this.getNode(vertexPair[0].getID()) == vertexPair[0] && 
      this.getNode(vertexPair[1].getID()) == vertexPair[1] &&
      this.getEdge(vertexPair[0], vertexPair[1]) == null && 
      vertexPair[0] != vertexPair[1]){
        //Kante einfuegen.
        Edge e = new Edge(vertexPair[0], vertexPair[1], pEdge.getWeight()); ///DAS WAR ICH!
        edges.append(pEdge); 
        edges.append(e); ///DAS WAR ICH AUCH...1
      }
    }
  }
  
  public void resetMarks(){
    vertices.toFirst();
    while (vertices.hasAccess()){ // BY INGE INTERNATIONAl developement inc
      vertices.getContent().setMark(false);
      vertices.next();
      //System.out.println("herlo");
    }
  }
  
   /**
   * Der Auftrag entfernt die Kante pEdge aus dem Graphen. Ist die Kante pEdge nicht 
   * im Graphen enthalten, passiert nichts.
   */
  public void removeEdge(Edge pEdge){
    //Kante aus Kantenliste des Graphen entfernen.
    edges.toFirst();
    while (edges.hasAccess()){
      if (edges.getContent() == pEdge){
        edges.remove();
      } else {
        edges.next();
      }
    }
  }
  
  /**
   * Der Auftrag setzt die Markierungen aller Knoten des Graphen auf pMark.
   */
  public void setAllVertexMarks(boolean pMark){
    vertices.toFirst();
    while (vertices.hasAccess()){
      vertices.getContent().setMark(pMark);
      vertices.next();
    }
  }

  /**
   * Der Auftrag setzt die Markierungen aller Kanten des Graphen auf pMark.
   */
  public void setAllEdgeMarks(boolean pMark){
    edges.toFirst();
    while (edges.hasAccess()){
      edges.getContent().setMark(pMark);
      edges.next();
    }
  }

  /**
   * Die Anfrage liefert true, wenn alle Knoten des Graphen mit true markiert sind, ansonsten false.
   */
  public boolean allVerticesMarked(){
    boolean result = true;
    vertices.toFirst();
    while (vertices.hasAccess()){
      if (!vertices.getContent().isMarked()){
        result = false;
      }
      vertices.next();
    }
    return result;
  }
  
  /**
   * Die Anfrage liefert eine neue Liste aller Knotenobjekte vom Typ List<Vertex>.
   * --> getNodes()!
   */
  public List<GraphNode> getVertices(){
    //Eine neue Liste mit allen GraphNode-Objekten erstellen.
    List<GraphNode> result = new List<GraphNode>();
    vertices.toFirst();
    while (vertices.hasAccess()){
      result.append(vertices.getContent());
      vertices.next();
    }
    //Aktuelles Element zum Anfang bewegen.
    result.toFirst();

    return result;
  }

  /**
   * Die Anfrage liefert eine neue Liste aller Kantenobjekte vom Typ List<Edge>.
   * Nicht in der Doku!
   */
  public List<Edge> getEdges(){
    //Eine neue Liste mit allen Edge-Objekten erstellen.
    List<Edge> result = new List<Edge>();
    edges.toFirst();
    while (edges.hasAccess()){
      result.append(edges.getContent());
      edges.next();
    }
    //Aktuelles Element zum Anfang bewegen.
    result.toFirst();

    return result;
  }

  /**
   * Die Anfrage liefert true, wenn alle Kanten des Graphen mit true markiert sind, ansonsten false.
   */
  public boolean allEdgesMarked(){
    boolean result = true;
    edges.toFirst();
    while (edges.hasAccess()){
      if (!edges.getContent().isMarked()){
        result = false;
      }
      edges.next();
    }
    return result;
  }

  /**
   * Die Anfrage liefert alle Nachbarn des Knotens pVertex als neue Liste vom Typ List<Vertex>. Hat der Knoten
   * pVertex keine Nachbarn in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so 
   * wird eine leere Liste zurueckgeliefert.
   */
  public List<GraphNode> getNeighbours(GraphNode pVertex){
    List<GraphNode> result = new List<GraphNode>();
    
    //Alle Kanten durchlaufen.
    edges.toFirst();
    while (edges.hasAccess()){
      
      //Wenn ein Knoten der Kante pVertex ist, den anderen als Nachbarn in die Ergebnisliste einfuegen.
      GraphNode[] vertexPair = edges.getContent().getVertices();
      if (vertexPair[0] == pVertex) {
        result.append(vertexPair[1]);
      }else { 
        if (vertexPair[1] == pVertex){
          result.append(vertexPair[0]);
        }
      }
      edges.next();
    }    
    return result;
  }

  /**
   * Die Anfrage liefert eine neue Liste alle inzidenten Kanten zum Knoten pVertex. Hat der Knoten
   * pVertex keine inzidenten Kanten in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so 
   * wird eine leere Liste zurueckgeliefert.
   */
  public List<Edge> getEdges(GraphNode pVertex){
    List<Edge> result = new List<Edge>();
    
    //Alle Kanten durchlaufen.
    edges.toFirst();
    while (edges.hasAccess()){
      
      //Wenn ein Knoten der Kante pVertex ist, dann Kante als inzidente Kante in die Ergebnisliste einfuegen.
      GraphNode[] vertexPair = edges.getContent().getVertices();
      if (vertexPair[0] == pVertex) {
        result.append(edges.getContent());
      } else{ 
        if (vertexPair[1] == pVertex){
          result.append(edges.getContent());
        }
      }
      edges.next();
    }    
    return result;
  }

  /**
   * Die Anfrage liefert die Kante, welche die Knoten pVertex und pAnotherVertex verbindet, 
   * als Objekt vom Typ Edge. Ist der Knoten pVertex oder der Knoten pAnotherVertex nicht 
   * im Graphen enthalten oder gibt es keine Kante, die beide Knoten verbindet, so wird null 
   * zurueckgeliefert.
   */
  public Edge getEdge(GraphNode pVertex, GraphNode pAnotherVertex){
    Edge result = null;
    
    //Kanten durchsuchen, solange keine passende gefunden wurde.
    edges.toFirst();
    while (edges.hasAccess() && result == null){
      
      //Pruefen, ob die Kante pVertex und pAnotherVertex verbindet.
      GraphNode[] vertexPair = edges.getContent().getVertices();
      //System.out.println(edges.getContent().toString());
      if ( (vertexPair[0].getID()).equals(pVertex.getID()) && (vertexPair[1].getID()).equals(pAnotherVertex.getID())
              || (vertexPair[0].getID()).equals(pAnotherVertex.getID()) && (vertexPair[1].getID()).equals(pVertex.getID()))
      {
        //Kante als Ergebnis merken.
        result = edges.getContent();
      } 
      edges.next();
    }    
    return result;
  }

}
