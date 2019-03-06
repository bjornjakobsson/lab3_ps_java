public class Edge <T>{

    private Vertex startVertex;
    private Vertex endVertex;
    private T weight;



    public Edge(Vertex startVertex, Vertex endVertex, T weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight=weight;
    }

    public Vertex getStartVertex(){
        return startVertex;
    }

    public Vertex getEndVertex(){
        return endVertex;
    }

    public T getWeight(){
        return weight;
    }

}
