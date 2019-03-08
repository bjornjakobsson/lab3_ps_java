import java.util.LinkedList;

public interface WeightInterface <T> {

    T compare(T a, T b);

    T sum(LinkedList<T> list);

    T f(T weight);

    T g(T weight);
}
