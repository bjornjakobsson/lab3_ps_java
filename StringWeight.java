import java.util.LinkedList;

public class StringWeight <T extends Comparable <T> > implements WeightInterface<StringWeight> {

    String weight = "";

    public StringWeight(String weight){
        this.weight = weight;
    }

    public String getWeight(){
        return this.weight;
    }
    //Compare as reverse alphabetical ordering
    @Override
    public StringWeight compare(StringWeight a, StringWeight b) {
        String aa = a.getWeight();
        String bb = b.getWeight();

        if(aa.compareTo(bb) < 0){
            return a;
        }
        else if(aa.compareTo(bb) == 0){
            return a;
        }
        else{
            return b;
        }

    }
    //Addition is defined as concatenating strings together
    @Override
    public StringWeight sum(LinkedList<StringWeight> list) {

        StringBuilder sb = new StringBuilder();
        for(StringWeight element: list ){
            sb.append(element.getWeight());

        }
        return new StringWeight(sb.substring(0, sb.length()));

    }

    @Override
    public StringWeight f(StringWeight weight) {
        return weight;
    }

    @Override
    public StringWeight g(StringWeight weight) {
        return weight;
    }
}
