/**
 * Class:       StringWeight
 * Description: An implementation of a weight object to be used in DAG.
 *              Uses Strings as weights, comparison is done as reverse alphabetical ordering
 *              Addition is defined as concatenation
 *
 * @param <T>   Used to make the graph polymorphic
 *
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 * @version     1.0
 */


import java.util.LinkedList;

public class StringWeight <T extends Comparable <T> > implements WeightInterface<StringWeight> {

    private String weight = "";

    /**
     * Constructor for StringWeight object.
     * @param weight the weight of the object at creation.
     */
    public StringWeight(String weight){
        this.weight = weight;
    }

    /**
     * Returns the weight of an object.
     * @return the weight to return.
     */
    public String getWeight(){
        return this.weight;
    }

    /**
     * Compares two StringWeight objects
     * @param a One of the StringWeight objects
     * @param b The other StringWeight object
     * @return returns the object that has the largest weight.
     *
     */
    @Override
    public StringWeight compare(StringWeight a, StringWeight b) {

        String aa = a.getWeight();
        String bb = b.getWeight();

        if(aa.compareTo(bb) > 0){

            return a;
        }
        else if(aa.compareTo(bb) == 0){
            return a;
        }
        else{
            return b;
        }

    }

    /**
     * Sums two StringWeight objects together. Addition is defined as appending one string to another.
     * @param list A list of objects to sum
     * @return Returns a new object that is the sum of all other objects in list.
     */
    @Override
    public StringWeight sum(LinkedList<StringWeight> list) {

        StringBuilder sb = new StringBuilder();
        for(StringWeight element: list ){
            sb.append(element.getWeight());

        }
        return new StringWeight(sb.substring(0, sb.length()));

    }

    /**
     * Returns the weight of the object
     * @param weight Object to be used
     * @return the weight of the object
     */
    @Override
    public StringWeight f(StringWeight weight) {
        return weight;
    }

    /**
     * Returns the weight of the object
     * @param weight Object to be used
     * @return the weight of the object
     */
    @Override
    public StringWeight g(StringWeight weight) {
        return weight;
    }
}
