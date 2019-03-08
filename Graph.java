import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Stack;

public class Graph <T>{

    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;

    public Graph(){
        this.vertices=new LinkedList<>();
        this.edges=new LinkedList<>();
    }

    public Graph(LinkedList<Vertex> vertices, LinkedList<Edge> edges){
        this.vertices=vertices;
        this.edges=edges;
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

        LinkedList<Vertex> tempVert = new LinkedList<>();
        LinkedList<Edge> tempEdge = new LinkedList<>();

        tempVert.addAll(vertices);
        tempEdge.addAll(edges);

        Graph temp = new Graph(tempVert,tempEdge);

        temp.edges.add(newEdge);

        try {
            topological_ordering(temp);
        }catch (CreatesCycleException e){
            System.out.println("Inserting edge between " + startId + " and "+ endId+ " creates cycle. ");
        }
        startVertex.incrementNeighbours();
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

    public T longest_path(int startId, int endId, WeightInterface wi){


        Vertex startVertex = vertices.get(startId);

        Vertex endVertex = vertices.get(endId);

        Stack<Vertex> crossroads=new Stack<>();
        LinkedList<Vertex> visited= new LinkedList<>();
        LinkedList<Vertex> currentPath= new LinkedList<>();
        LinkedList<LinkedList<Vertex>> allPaths = new LinkedList<>();
        Stack<Vertex> unVisited = new Stack<>();

        Vertex current=new Vertex();

        for (Vertex v: getOutgoing(startVertex)) {
            unVisited.push(v);
        }
        if(startVertex.getNeighbours()>1){
            System.out.println("Går in i push crossroads");
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
            //Når slutnod
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
                for (Vertex v: getOutgoing(current)) {
                    unVisited.push(v);
                }
            }


            // Fall för att pusha til crossroads
            if((current.getNeighbours()==0 || visited.containsAll(getOutgoing(current)) || current.getId()==endVertex.getId() ) && !crossroads.isEmpty()){

                boolean found = false;
                Vertex temp = new Vertex();
                while(!found){
                    temp = currentPath.removeLast();
                    if(temp.getId() == crossroads.peek().getId()){
                        found = true;
                    }
                }
                currentPath.add(temp);

                LinkedList<Vertex> trueNeighbours = getOutgoing(temp);
                if(trueNeighbours.contains(endVertex)){
                    trueNeighbours.remove(endVertex);
                }

                if(visited.containsAll(trueNeighbours)){
                    crossroads.pop();
                    currentPath.removeLast();
                }
            }

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
          //  System.out.println(" ");
            weights.add(tmp);
        }

        T largest = (T)startVertex.getWeight();

        for (LinkedList<T> list: weights) {
            T temp=(T)wi.sum(list);
            if(wi.compare(temp,largest)==temp){
                largest=temp;
            }
        }

        return largest;

    }

    public Edge getEdge(Vertex src, Vertex dest){
        for (Edge e: edges) {
            if(e.getStartVertex().equals(src) && e.getEndVertex().equals(dest)){
                return e;
            }
        }
        return null;
    }

}