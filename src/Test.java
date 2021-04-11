import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("1");
        arr.add("1");

        //arr.add(1, "abc");
        arr.set(1, "abc");
        System.out.println(Arrays.toString(arr.toArray()));
    }
}
