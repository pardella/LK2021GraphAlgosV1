package graphalgorithmen; 
/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 Klasse GraphNode
 </p>
 * <p>
 Die Klasse GraphNode stellt einen einzelnen Knoten eines Graphen dar. Jedes Objekt 
 dieser Klasse verfuegt ueber eine im Graphen eindeutige ID als String und kann diese 
 ID zurueckliefern. Darueber hinaus kann eine Markierung gesetzt und abgefragt werden.
 </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Oktober 2015
 */
public class GraphNode{
  //Einmalige ID des Knotens und Markierung
  private String id;
  private boolean mark;
  
  /**
  * Ein neues Objekt vom Typ Vertex wird erstellt. Seine Markierung hat den Wert false.
  */
  public GraphNode(String pID){
    id = pID;
    mark = false;
  }
  
  /**
  * Die Anfrage liefert die ID des Knotens als String.
  */
  public String getID(){
    return new String(id);
  }
  
  /**
  * Der Auftrag setzt die Markierung des Knotens auf den Wert pMark.
  */
  public void setMark(boolean pMark){
    mark = pMark;
  }
  
  /**
  * Die Anfrage liefert true, wenn die Markierung des Knotens den Wert true hat, ansonsten false.
  */
  public boolean isMarked(){
    return mark;
  }
  
  public String toString()
  {
      String s=id+" ";
      if (mark) s+="marked";
      else s+="unmarked";
      return s;
  }
  
}
