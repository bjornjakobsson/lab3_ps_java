import java.util.LinkedList;

public class Vertex <T> {

    private int id;
    private T weight;
    private int neighbours=0;
    private int steps=0;


    private boolean permMark;
    private boolean tempMark;

    public Vertex (int id, T weight){
        this.id=id;
        this.weight=weight;
        this.permMark=false;
        this.tempMark=false;
    }
    public Vertex(){

    }

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
    public void incrementSteps(){
        this.steps += 1;
    }

    public int getNeighbours(){
        return neighbours;
    }

    public int getSteps(){
        return steps;
    }
}
