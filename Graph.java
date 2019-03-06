import java.util.LinkedList;
import java.util.Stack;

public class Graph <T>{

    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;

    public Graph(){
        this.vertices=new LinkedList<>();
        this.edges=new LinkedList<>();
    }


    public int add_vertex(T weight) throws IllegalAccessException{
        Vertex newVertex = new Vertex(vertices.size()+1, weight);


        if(!vertices.isEmpty()){
            Vertex tmp = vertices.getFirst();
            if(tmp.getWeight().getClass()!=weight.getClass()){
                throw new IllegalAccessException("Not the same weight type");
            }else{
                vertices.add(newVertex);
            }
        }else{
            vertices.add(newVertex);
        }
        return newVertex.getId();
    }

    public void add_edge(int startId, int endId, T weight){

        Vertex startVertex=new Vertex();
        Vertex endVertex=new Vertex();

        for (Vertex v : vertices) {
            if(v.getId() == startId){
                startVertex=v;
            }
            if(v.getId()==endId){
                endVertex=v;
            }
        }

        Edge newEdge = new Edge(startVertex,endVertex, weight);
        edges.add(newEdge);

    }

    public LinkedList<Vertex> topological_ordering (Graph g) throws CreatesCycleException{


        LinkedList<Vertex> orderedVertices = new LinkedList<>();
        LinkedList<Vertex> unmarkedVertices = g.vertices;

        while(!unmarkedVertices.isEmpty()) {
            Vertex v = unmarkedVertices.pop();
            visit(v,orderedVertices);

        }
        return orderedVertices;


    }

    public T f(Vertex v){
        return (T) v.getWeight();
    }
    public T g(Edge e){
        return (T) e.getWeight();
    }

/*    public T longest_path(Vertex startNode, Vertex endNode){

        Stack notVisited= new Stack();
        notVisited.push(getOutgoing(startNode));


    }*/


    private void visit(Vertex v,  LinkedList<Vertex> orderedVertices) throws CreatesCycleException{
        if(v.isPermMark()) {
            return;
        }
        if(v.isTempMark()) {
            throw new CreatesCycleException();
        }
        v.setTempMark(true);

        for (Vertex m: getOutgoing(v)) {
            visit(m,orderedVertices);
        }
        v.setPermMark(true);
        orderedVertices.push(v);


    }

    public LinkedList<Vertex> getOutgoing(Vertex v){

        LinkedList<Vertex> outGoingVertices=new LinkedList<>();

        for (Edge e:edges) {
            if(v.getId()==e.getStartVertex().getId()){
                outGoingVertices.add(e.getEndVertex());
            }

        }
        return outGoingVertices;

    }



























    public LinkedList<Vertex> getVertices(){
        return vertices;
    }

    public LinkedList<Edge> getEdges(){
        return edges;
    }

    public void printVertices(){
        for (Vertex v : vertices) {
            System.out.print(v.getId()+" ");
        }
        System.out.println(" ");
    }

    public void printEdges(){

        for (Edge e: edges) {
            System.out.println(e.getStartVertex().getId()+ " -> "+ e.getEndVertex().getId());
        }

    }

    public void longestPath(Graph g){

    }

}
