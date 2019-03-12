import sun.awt.image.ImageWatched;

import java.util.LinkedList;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main (String[] input) {
        Main main = new Main();
        main.test_graph();
        main.test_string_weight();
        main.test_cyclic_string();
        main.test_cyclic_int();
    }

    /**
     * Tests the case where the DAG uses StringWeight as weight.
     * Creates weights and adds vertices with those wights,
     * then adds edges between all weights and tests for a longest path
     *
     * This test should create an acyclic graph
     */
    @SuppressWarnings("Duplicates")
    private void test_string_weight(){

        Graph<StringWeight> g = new Graph<>();

        StringWeight a = new StringWeight("a");
        StringWeight b = new StringWeight("b");
        StringWeight c = new StringWeight("c");
        StringWeight d = new StringWeight("d");
        StringWeight e = new StringWeight("e");
        StringWeight f = new StringWeight("f");
        StringWeight h = new StringWeight("h");
        StringWeight i = new StringWeight("i");

        try {
            g.add_vertex(a);
            g.add_vertex(b);
            g.add_vertex(c);
            g.add_vertex(d);
            g.add_vertex(e);
            g.add_vertex(f);
            g.add_vertex(h);
            g.add_vertex(i);

        } catch (IllegalAccessException e1) {
            System.out.println(e.toString());
        }

        g.add_edge(1,2, a);
        g.add_edge(2,3, a);
        g.add_edge(3,4, a);
        g.add_edge(4,5, a);
        g.add_edge(5,6, a);
        g.add_edge(6,7, a);
        g.add_edge(7,8, a);

        try {
            g.topological_ordering(g);
        } catch (CreatesCycleException e1) {
            System.out.println("Could do topological ordering, graph is cyclic" + e1.toString());
        }

        try {
            StringWeight longest = g.longest_path(1,8, a);
            System.out.println("The weight of the longest path is: " + longest.getWeight());
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }


    }

    /**
     * Tests to create a DAG with IntWeight as weights.
     *
     * This test should not add all edges to the graph.
     */
    @SuppressWarnings("Duplicates")
    private void test_graph(){
        Graph<IntWeight> g = new Graph<>();

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

        try {
            g.topological_ordering(g);

        } catch (CreatesCycleException e1) {
            System.out.println("Could do topological ordering, graph is cyclic" + e1.toString());
        }


        try{
            IntWeight weight= g.longest_path(1,8, a);
            System.out.println("\nThe weight of the longest path is: "+ weight.getWeight());
        }catch (IllegalAccessException e1){
            System.out.println(e1.toString());
        }

    }

    /**
     * Creates a cyclic graph and tries to find the longest path for it.
     *
     * Test should not add all edges to graph
     */
    @SuppressWarnings("Duplicates")
    private void test_cyclic_string(){

        Graph<StringWeight> g = new Graph<StringWeight>();

        StringWeight a = new StringWeight("a");
        StringWeight b = new StringWeight("b");
        StringWeight c = new StringWeight("c");
        StringWeight d = new StringWeight("d");
        StringWeight e = new StringWeight("e");
        StringWeight f = new StringWeight("f");
        StringWeight h = new StringWeight("h");
        StringWeight i = new StringWeight("i");

        try {
            g.add_vertex(a);
            g.add_vertex(b);
            g.add_vertex(c);
            g.add_vertex(d);
            g.add_vertex(e);
            g.add_vertex(f);
            g.add_vertex(h);
            g.add_vertex(i);

        } catch (IllegalAccessException e1) {
            System.out.println("Could not add vertex: " + e.toString());
        }

        g.add_edge(1,2,a);
        g.add_edge(2,3,a);
        g.add_edge(3,4,a);
        g.add_edge(4,5,a);
        g.add_edge(5,6,a);
        g.add_edge(6,1,a);

        try {
            g.topological_ordering(g);
        } catch (CreatesCycleException e1) {
            System.out.println("Could do topological ordering, graph is cyclic" + e1.toString());
        }

        try {
            StringWeight longest = g.longest_path(1,6,a);
            System.out.println("\nThe weight of longest path is: " + longest.getWeight());
        } catch (IllegalAccessException e1) {
            System.out.println("Could not find longest path, graph is cyclic: " + e1.toString());
        }

    }

    /**
     * Tests to create cyclic graph with IntWeight as weight
     *
     * Test should not add edges that creates a cycle to the graph
     */
    private void test_cyclic_int() {

        Graph<IntWeight> g = new Graph<>();

        IntWeight a = new IntWeight(1);
        IntWeight b = new IntWeight(2);
        IntWeight c = new IntWeight(3);
        IntWeight d = new IntWeight(4);
        IntWeight e = new IntWeight(5);
        IntWeight f = new IntWeight(6);
        IntWeight h = new IntWeight(7);
        IntWeight i = new IntWeight(8);


        try {
            g.add_vertex(a);
            g.add_vertex(b);
            g.add_vertex(c);
            g.add_vertex(d);
            g.add_vertex(e);
            g.add_vertex(f);
            g.add_vertex(h);
            g.add_vertex(i);

        } catch (IllegalAccessException ex) {
            System.out.println(e.toString());
        }

        g.add_edge(1, 2, a);
        g.add_edge(2, 3, a);
        g.add_edge(3, 4, a);
        g.add_edge(4, 5, a);
        g.add_edge(5, 6, a);
        g.add_edge(6, 7, a);
        g.add_edge(7, 8, a);
        g.add_edge(8, 9, a);
        g.add_edge(9, 1, a);

        try {
           g.topological_ordering(g);

        } catch (CreatesCycleException e1) {
            System.out.println("Could do topological ordering, graph is cyclic" + e1.toString());
        }


        try {
            IntWeight weight = g.longest_path(1, 8, a);
            System.out.println("\nThe weight of the longest path is: " + weight.getWeight());
        } catch (IllegalAccessException e1) {
            System.out.println("Could not find longest path, graph is cyclic: " + e1.toString());

        }
    }


}
