/**
 * Class:       Vertex
 * Description: A representation of a vertex in a graph.
 * @param <T>
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 */

public class Vertex <T> {

    private int id;
    private T weight;
    private int neighbours=0;


    private boolean permMark;
    private boolean tempMark;

    /**
     * Constructor
     * Creates a vertex with id and weight
     * @param id the id of the vertex
     * @param weight the weight of the vertex
     */
    public Vertex (int id, T weight){
        this.id=id;
        this.weight=weight;
        this.permMark=false;
        this.tempMark=false;
    }

    /**
     * Constructor for vertex
     * Creates an empty vertex with no information in it.
     */
    public Vertex(){

    }

    //Getters and setters
    public int getId(){
        return id;
    }

    public T getWeight(){
        return weight;
    }

    public boolean isPermMark() {
        return permMark;
    }

    public void setPermMark(boolean permMark) {
        this.permMark = permMark;
    }

    public boolean isTempMark() {
        return tempMark;
    }

    public void setTempMark(boolean tempMark) {
        this.tempMark = tempMark;
    }

    public void incrementNeighbours(){
        this.neighbours += 1;
    }

    public int getNeighbours(){
        return neighbours;
    }

}
