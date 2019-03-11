/**
 * Class:       Edge
 * Description: A representation of an edge in a graph.
 *              Has a start and end node.
 * @param <T>
 */

public class Edge <T>{

    private Vertex startVertex;
    private Vertex endVertex;
    private T weight;


    /**
     * Constructor
     * @param startVertex
     * @param endVertex
     * @param weight
     */
    public Edge(Vertex startVertex, Vertex endVertex, T weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight=weight;
    }

    //Getters and setter
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
