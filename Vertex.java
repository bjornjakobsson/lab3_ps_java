import java.util.LinkedList;

public class Vertex <T> {

    private int id;
    private T weight;



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
}
