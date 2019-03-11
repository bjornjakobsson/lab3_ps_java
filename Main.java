import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class Main {
    public static void main (String[] input) {
        Main main = new Main();
        main.test_graph();
    }

    private void test_graph(){
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
        g.add_edge(2,8,a);

        g.add_edge(3,6,a);
        g.add_edge(3,7,a);
        g.add_edge(3,8,a);

        g.add_edge(4,8,a);

        try{
            IntWeight weight= (IntWeight) g.longest_path(1,8, a);
            System.out.println("\nThe largest weight is: "+ weight.getWeight());
        }catch (IllegalAccessException e1){
            System.out.println(e1.toString());
        }
        try{
            IntWeight weight= (IntWeight) g.longest_path(1,8, a);
            System.out.println("\nThe largest weight is: "+ weight.getWeight());
        }catch (IllegalAccessException e1){
            System.out.println(e1.toString());
        }

    }
    private Graph cpyGraph(Graph g){

        LinkedList<Vertex> vertices = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();
        LinkedList<Vertex> leafs = new LinkedList<>();

        Graph newGraph = new Graph(vertices,edges,leafs);
        return newGraph;
    }



}
