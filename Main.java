import java.util.LinkedList;

public class Main {
    public static void main (String[] input) {
        Graph g = new Graph();

        g.add_vertex(1);
        g.add_vertex(2);
        g.add_vertex(3);
        g.add_vertex(4);
        g.add_vertex(5);
        g.add_vertex(6);


        g.add_edge(6,3,7);
        g.add_edge(6, 1, 0);
        g.add_edge(5,1,4);
        g.add_edge(5,2,5);
        g.add_edge(3,4,5);
        g.add_edge(4,2,4);

        try{
            LinkedList<Vertex> sorted = g.topological_ordering(g);

            for (Vertex v:sorted) {
                System.out.print(v.getId() + " ");
            }
        }catch (CreatesCycleException e){
            System.out.println("Creates a cycle mufakka");
        }


    }



}
