public class IntWeight<T extends Comparable <T> > implements WeightInterface<Integer> {

    int weight = 0;


    public IntWeight(int weight){
        this.weight=weight;
    }

    @Override
    public Integer compare(Integer a, Integer b) {


        return a.compareTo(b);

    }

    @Override
    public Integer sum(Integer a, Integer b) {

        int sum = a+b;
        return sum;
    }


    public int getWeight(){
        return weight;
    }
}
