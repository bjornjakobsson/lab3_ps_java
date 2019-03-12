/**
 * Class:       Graph
 * Description: A class that represents a directed acyclic graph.
 *              If all edges is added by the add_edge method. It is ensured that the graph
 *              is truly acyclic and directed.
 *
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 * @version     1.0
 */


import java.util.LinkedList;
import java.util.Stack;

public class Graph <T>{

    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;
    private LinkedList<Vertex> leafs;

    /**
     * Constructor for Graph
     * Used to create an empty Graph
     */
    public Graph(){
        this.vertices=new LinkedList<>();
        this.edges=new LinkedList<>();
        this.leafs=new LinkedList<>();
    }

    /**
     * Constructor for Graph
     * To be used if vertices and edges are known when creating graph
     * @param vertices a list of vertices
     * @param edges a list of edges
     */
    public Graph(LinkedList<Vertex> vertices, LinkedList<Edge> edges){
        this.vertices=vertices;
        this.edges=edges;
        this.leafs=new LinkedList<>();
    }

    /**
     * Constructor for Graph
     * To be used if the vertices, edges and leafs are known when creating a graph
     * @param vertices a list of vertices
     * @param edges a list of edges
     * @param leafs a list of leafs
     */
    public Graph(LinkedList<Vertex> vertices, LinkedList<Edge> edges, LinkedList<Vertex> leafs){
        this.vertices=vertices;
        this.edges=edges;
        this.leafs=leafs;
    }

    /**
     * Adds a vertex to the graph. It needs to have the same weight type as the other vertices already in the graph.
     * If there graph is empty, the given type is set as the vertex type.
     * @param weight the weight of the vertex
     * @return
     * @throws IllegalAccessException
     */
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

    /**
     * Adds an edge between two vertices. If the edge created by the two vertices creates a cycle, the edge is discarded
     * and the edge does not get added to the graph.
     * @param startId start id of edge
     * @param endId end id of edge
     * @param weight weight of the edge
     */
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

        LinkedList<Vertex> tempV = new LinkedList<>();
        LinkedList<Edge> tempE = new LinkedList<>();
        LinkedList<Vertex> tempL = new LinkedList<>();

        tempV.addAll(vertices);
        tempE.addAll(edges);
        tempL.addAll(leafs);
        tempE.add(newEdge);

        Graph newGraph = new Graph(tempV,tempE,tempL);
        newGraph.updateLeafs(newGraph);


        try {
            newGraph.isCyclic(newGraph);
            this.edges.add(newEdge);
        }catch (CreatesCycleException e){
            System.out.println("Edge "+ newEdge.getStartVertex().getId() +" -> "+ newEdge.getEndVertex().getId()+ " creates cycle: Will not be added to graph");
        }
        startVertex.incrementNeighbours();
        newGraph.updateLeafs(newGraph);
        updateLeafs(this);


    }

    /**
     * topological_ordering
     * Sorts the vertices in a topological ordering. Can only be done if the graph is acyclic.
     * @param g the graph to look through
     * @return a list of vertices
     * @throws CreatesCycleException
     */
    public LinkedList<Vertex> topological_ordering (Graph g) throws CreatesCycleException{

        LinkedList<Vertex> tempV = new LinkedList<>();
        LinkedList<Edge> tempE = new LinkedList<>();
        LinkedList<Vertex> tempL = new LinkedList<>();

        tempV.addAll(g.vertices);
        tempE.addAll(g.edges);
        tempL.addAll(g.leafs);

        Graph tempGraph = new Graph(tempV,tempE,tempL);

        LinkedList<Vertex> orderedVertices = new LinkedList<>();
        LinkedList<Vertex> unmarkedVertices =tempGraph.vertices;

        while(!unmarkedVertices.isEmpty()) {
            Vertex v = unmarkedVertices.pop();
            visit(v,orderedVertices);
        }
        return orderedVertices;
    }

    /**
     * longest_path
     * Returns the weight of the longest path between two vertices.
     * The longest path is not determined by the "ammount of steps" from the start vertex to the end vertex.
     * Is is determined by the total weight of the path.
     * @param startId start id of search
     * @param endId end id of search
     * @param wi An object that implements the WeightInterface interface
     * @return
     */
    public T longest_path(int startId, int endId, WeightInterface wi)throws IllegalAccessException{
        Vertex startVertex=new Vertex();
        Vertex endVertex= new Vertex();

        for (Vertex v : vertices) {
            if(v.getId() == startId){
                startVertex=v;
            }
            if(v.getId()==endId){
                endVertex=v;
            }
        }

        Stack<Vertex> crossroads=new Stack();
        LinkedList<Vertex> visited= new LinkedList<>();
        LinkedList<Vertex> currentPath= new LinkedList<>();
        LinkedList<LinkedList<Vertex>> allPaths = new LinkedList<>();
        Stack<Vertex> unVisited = new Stack<>();
        Vertex current;

        for (Vertex v: getOutgoing(startVertex,this)) {
            unVisited.push(v);
        }
        if(startVertex.getNeighbours()>1){
            crossroads.push(startVertex);
        }
        currentPath.add(startVertex);

        while (!unVisited.isEmpty()){
            current=unVisited.pop();
            if(visited.contains(current)){
                continue;
            }
            if(current.getId() != endVertex.getId()){
                visited.add(current);
            }
            currentPath.add(current);
            //Reaches endnode
            if(current.getId()==endVertex.getId()){
                LinkedList<Vertex> tmp = new LinkedList<>();
                for (Vertex v : currentPath) {
                    tmp.add(v);
                }
                allPaths.add(tmp);
            }
            if(current.getNeighbours()>1 && current.getId() != endVertex.getId()){
                crossroads.push(current);
            }

            if(current.getId() != endVertex.getId()){
                for (Vertex v: getOutgoing(current,this)) {
                    unVisited.push(v);
                }
            }

            // Fall för att pusha til crossroads
            if((current.getNeighbours()==0 || visited.containsAll(getOutgoing(current,this)) || current.getId()==endVertex.getId()) && !crossroads.isEmpty()){
                boolean found = false;
                Vertex temp = new Vertex();
                while(!found){
                    temp = currentPath.removeLast();
                    if(temp.getId() == crossroads.peek().getId()){
                        found = true;
                    }
                }
                currentPath.add(temp);

                LinkedList<Vertex> trueNeighbours = getOutgoing(temp,this);

                if(trueNeighbours.contains(endVertex)){
                    trueNeighbours.remove(endVertex);
                }

                if(visited.containsAll(trueNeighbours)&&crossroads.size()>1){
                    crossroads.pop();
                    currentPath.removeLast();
                }
            }

        }

        return getLargest(startVertex,endVertex,allPaths,wi);
    }

    /**
     * Compares weights of all paths and returns the largest one.
     * All comparisons/additions is performed by the methods 'compare' and 'add'
     * defined in the WeightInterface.
     * @param startVertex
     * @param endVertex
     * @param allPaths
     * @param wi
     * @return
     * @throws IllegalAccessException
     */
    private T getLargest(Vertex startVertex, Vertex endVertex, LinkedList<LinkedList<Vertex>> allPaths, WeightInterface wi)
    throws IllegalAccessException{
        T largest = (T)startVertex.getWeight();
        if (allPaths.isEmpty()){
            throw new IllegalAccessException("No path from "+startVertex.getId() + " to " + endVertex.getId());
        }
        LinkedList<LinkedList<T>> weights = new LinkedList<>();


        //Creates a list of lists where each list contains a list of node weights.
        for (LinkedList<Vertex> list : allPaths) {
            LinkedList<T> tmp = new LinkedList<>();

            //Adds all vertex weights, with the help of f
            for (Vertex v : list) {
                tmp.add((T)wi.f(v.getWeight()));
            }
            for (int i = 0; i<list.size()-1;i++){
                Edge e = getEdge(list.get(i),list.get(i+1));
                //Adds all edge weight, with the help of g-+
                if(e!=null){
                    tmp.add((T)wi.g(e.getWeight()));
                }

            }
            weights.add(tmp);

        }

        for (LinkedList<T> list: weights) {
            T temp=(T)wi.sum(list);

            if(wi.compare(temp,largest)==temp){
                largest=temp;
            }
        }
        return largest;
    }

    /**
     * Prints a path of vertices
     * @param path
     */
    private void printPath(LinkedList<Vertex> path){
        System.out.println("Path:");
        for (Vertex v : path) {
            System.out.print(v.getId()+" ");
        }
        System.out.println(" ");
    }
    /**
     * isCyclic
     * Tests if a graph is cyclic
     * @param g
     * @return
     * @throws CreatesCycleException
     */
    public boolean isCyclic (Graph g) throws CreatesCycleException{

        if(g.vertices.isEmpty() || g.edges.isEmpty()){
            return false;
        }else if(g.leafs.isEmpty()){
            throw new CreatesCycleException();
        }
        else{
            return isCyclic(removeAllEdgesToVertex((Vertex)g.leafs.removeLast(),g));
        }
    }

    /**
     * Removes all edges coming in to a vertex
     * @param v
     * @param g
     * @return
     */
    private Graph removeAllEdgesToVertex(Vertex v,Graph g){
        LinkedList<Edge> theEdges=new LinkedList<>();
        theEdges.addAll(g.edges);
        if(g.vertices.size()==1){
            g.vertices.removeFirst();
            g.updateLeafs(g);
            return g;
        }
        for (Edge e: theEdges) {
            if(v.getId()==e.getEndVertex().getId()){
                g.edges.remove(e);
                g.vertices.remove(v);
            } else{
                g.vertices.remove(v);
            }
        }
        g.updateLeafs(g);
        return g;

    }

    /**
     * Visits a node and is recursivly called to visit its neighbours.
     * @param v
     * @param orderedVertices
     * @throws CreatesCycleException
     */
    private void visit(Vertex v,  LinkedList<Vertex> orderedVertices) throws CreatesCycleException{
        if(v.isPermMark()) {
            return;
        }
        if(v.isTempMark()) {
            throw new CreatesCycleException();
        }
        v.setTempMark(true);
        for (Vertex m: getOutgoing(v,this)) {
            visit(m,orderedVertices);
        }
        v.setPermMark(true);
        orderedVertices.push(v);
    }

    /**
     * Returns a list of all off the outgoing the neighbours to the vertex
     * @param v
     * @return
     */
    private LinkedList<Vertex> getOutgoing(Vertex v, Graph g){

        LinkedList<Vertex> outGoingVertices=new LinkedList<>();
        LinkedList<Edge> theEdges = g.edges;

        for (Edge e: theEdges) {
            if(v.getId()==e.getStartVertex().getId()){
                outGoingVertices.add(e.getEndVertex());
            }

        }
        return outGoingVertices;
    }

    public LinkedList<Vertex> getIncoming (Vertex v, Graph g){

        LinkedList<Vertex> incomingVertices=new LinkedList<>();
        LinkedList<Edge> theEdges = g.edges;

        for (Edge e: theEdges) {
            if(v.getId()==e.getEndVertex().getId()){
                incomingVertices.add(e.getStartVertex());
            }

        }
        return incomingVertices;
    }

    /**
     * Updates the leaf list of a graph
     */
    private void updateLeafs(Graph g){
        g.leafs.clear();
        if(g.vertices.size()==1){
            g.leafs.addAll(g.vertices);
            return;
        }
        for (int i = 0; i<g.vertices.size();i++){
            Vertex v = (Vertex)g.vertices.get(i);
            if(getOutgoing(v,g).isEmpty()&&!getIncoming(v,g).isEmpty()){
                g.leafs.add(v);
            }
        }

    }

    /**
     *
     * @param g
     * @return
     */
    private LinkedList<Vertex> getLeafs(Graph g){
        for (int i = 0; i<g.getVertices().size();i++){
            Vertex v = (Vertex) g.getVertices().get(i);

            if(getOutgoing(v,g).isEmpty()){
                leafs.add(v);
            }
        }

        return leafs;
    }

    /**
     *
     * @param list
     */
    public void printVertices(LinkedList<Vertex> list){
        System.out.println("Vertices: ");
        for (Vertex v : list) {
            System.out.print(v.getId()+ " ");
        }
        System.out.println();
    }

    /**
     *
     * @param list
     */
    public void printEdges(LinkedList<Edge> list){
        System.out.println("Edges: ");
        for (Edge e : list) {
            System.out.println(e.getStartVertex().getId() +" -> "+ e.getEndVertex().getId()+" ");
        }
        System.out.println();
    }

    /**
     *
     * @param list
     */
    public void printLeafs(LinkedList<Vertex> list){
        System.out.println("Leafs: ");
        for (Vertex v : list) {
            System.out.print(v.getId()+ " ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public LinkedList<Vertex> getVertices(){
        return vertices;
    }

    /**
     *
     * @return
     */
    public LinkedList<Edge> getEdges(){
        return edges;
    }

    /**
     *
     * @param src
     * @param dest
     * @return
     */
    public Edge getEdge(Vertex src, Vertex dest){
        for (Edge e: edges) {
            if(e.getStartVertex().equals(src) && e.getEndVertex().equals(dest)){
                return e;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public LinkedList<Vertex> getLeafs(){
        return this.leafs;
    }

}