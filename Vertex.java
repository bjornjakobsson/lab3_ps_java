import java.util.LinkedList;

public class Vertex {

    private int id;
    private int weight;
    private boolean permMark;
    private boolean tempMark;

    public Vertex (int id, int weight){
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
    public int getWeight(){
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
