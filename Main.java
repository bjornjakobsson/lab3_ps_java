import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class Main<T> {
    public static void main (String[] input) {
        Graph g = new Graph();

        IntWeight a = new IntWeight(1);
        IntWeight b = new IntWeight(2);
        IntWeight c = new IntWeight(3);
        IntWeight d = new IntWeight(4);
        IntWeight e = new IntWeight(5);
        IntWeight f = new IntWeight(6);
        IntWeight h = new IntWeight(7);
        IntWeight i = new IntWeight(8);

        try{
            g.add_vertex(a);
            g.add_vertex(b);
            g.add_vertex(c);
            g.add_vertex(d);
            g.add_vertex(e);
            g.add_vertex(f);
            g.add_vertex(h);
            g.add_vertex(i);
        }catch (IllegalAccessException ex){
            System.out.println(e.toString());
        }

        g.add_edge(1,2,a);
        g.add_edge(1,3,a);

        g.add_edge(2,4,a);
        g.add_edge(2,5,a);
        g.add_edge(4,8,a);

        g.add_edge(3,6,a);
        g.add_edge(3,7,a);
        g.add_edge(3,8,a);

        g.add_edge(6,8,a);

        IntWeight weight= (IntWeight) g.longest_path3((Vertex) g.getVertices().getFirst(), (Vertex) g.getVertices().getLast(), a);
        System.out.println("The largest weight is: "+ weight.getWeight());


        // g.longest_path4((Vertex)g.getVertices().getFirst(),(Vertex)g.getVertices().getLast());
    }



}
