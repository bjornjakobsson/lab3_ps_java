import java.util.LinkedList;

public class IntWeight<T extends Comparable <T> > implements WeightInterface<IntWeight> {

    int weight = 0;


    public IntWeight(int weight){
        this.weight=weight;
    }

    public int getWeight(){
        return weight;
    }

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

    @Override
    public IntWeight sum(LinkedList<IntWeight> list) {
        int sum = 0;
        for (IntWeight elem: list) {
            sum+=elem.getWeight();
        }
        return new IntWeight(sum);
    }

    @Override
    public IntWeight f(IntWeight weight) {
        return weight;
    }

    @Override
    public IntWeight g(IntWeight weight) {
        return weight;
    }
}
