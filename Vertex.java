/**
 * Class:       Vertex
 * Description: A representation of a vertex in a graph.
 * @param <T>
 */

public class Vertex <T> {

    private int id;
    private T weight;
    private int neighbours=0;


    private boolean permMark;
    private boolean tempMark;

    /**
     * Constructor
     * @param id
     * @param weight
     */
    public Vertex (int id, T weight){
        this.id=id;
        this.weight=weight;
        this.permMark=false;
        this.tempMark=false;
    }

    /**
     * Constructor
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
