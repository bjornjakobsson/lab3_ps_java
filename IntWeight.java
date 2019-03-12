/**
 * Class:       IntWeight
 * Description: An implementation of a weight object to be used in DAG.
 *              Comparison is done with normal integer comparison, same with addition.
 *
 * @param <T>   Used to make the graph polymorphic
 *
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 * @version     1.0
 */
import java.util.LinkedList;

public class IntWeight<T extends Comparable <T> > implements WeightInterface<IntWeight> {
    //Holds the weight of the object
    private int weight;

    /**
     * Constructor for IntWeight
     * @param weight the weight the object is to be created with
     */
    public IntWeight(int weight){

        this.weight=weight;
    }

    /**
     * Returns the weight of a given object
     * @return the weight of the object
     */
    public int getWeight(){

        return weight;
    }

    /**
     * Compares two IntWeight objects, comparison is done with simple integer comparison
     * @param a one of the objects to compare
     * @param b the other object to compare
     * @return returns the biggest of the objects
     */
    @Override
    public IntWeight compare(IntWeight a, IntWeight b) {
        Integer a_val = a.getWeight();
        Integer b_val = b.getWeight();

        if(a_val>b_val){
            return a;
        }else if(a_val.equals(b_val)){
            return new IntWeight(0);
        }else {
            return b;
        }
    }

    /**
     * Sums a list of objects
     * Sum with IntWeight is defined as sum of two integers
     * @param list the list of objects to sum
     * @return returns the sum of all objects in list
     */
    @Override
    public IntWeight sum(LinkedList<IntWeight> list) {
        int sum = 0;
        for (IntWeight elem: list) {
            sum+=elem.getWeight();
        }
        return new IntWeight(sum);
    }

    /**
     * Returns the weight of an object
     * @param weight the object to use
     * @return the weight of an object
     */
    @Override
    public IntWeight f(IntWeight weight)
    {
        return weight;
    }
    /**
     * Returns the weight of an object
     * @param weight the object to use
     * @return the weight of an object
     */
    @Override
    public IntWeight g(IntWeight weight) {
        return weight;
    }
}
