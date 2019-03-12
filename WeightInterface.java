/**
 * Class:       WeightInterface
 * Description: An interface to define behaviour of a Weight to be used in a DAG.
 *              Comparison and addition can be defined however the user wishes.
 *
 * @author      Emilia Modig, Björn Jakobsson, Johan Huusko
 * @version     1.0
 */

import java.util.LinkedList;

public interface WeightInterface <T> {

    T compare(T a, T b);

    T sum(LinkedList<T> list);

    T f(T weight);

    T g(T weight);
}
