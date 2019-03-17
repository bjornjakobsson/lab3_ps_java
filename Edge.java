/**
 * Class:       Edge
 * Description: A representation of an edge in a graph.
 *              Has a start and end node.
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 * @param <T>   Type of weight should be specified when creating an edge
 */

public class Edge <T>{

    private Vertex startVertex;
    private Vertex endVertex;
    private T weight;


    /**
     * Constructor for en Edge object
     * @param startVertex start vertex of the edge
     * @param endVertex end vertex of the edge
     * @param weight weight of the edge
     */
    public Edge(Vertex startVertex, Vertex endVertex, T weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight=weight;
    }

    /**
     * Used to return start index of en edge object
     * @return returns the start index of the edge
     */
    public Vertex getStartVertex() {
        return startVertex;

    }

    /**
     * Get end vertex for an edge
     * @return returns the end vertex of an edge
     */
    public Vertex getEndVertex() {
        return endVertex;

    }

    /**
     * Returns the weight of an edge
     * @return the weight of an edge
     */
    public T getWeight(){
        return weight;
    }

}
