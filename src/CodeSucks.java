import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CodeSucks {

    public static void main(String args[]) {

        //List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Optional<Double> first = numbers.stream()
                .filter(CodeSucks::GT3)
                .filter(CodeSucks::isEven)
                .map(CodeSucks::doDouble)
                .findFirst();

        if(!first.isEmpty()) {
            System.out.println(first.get());
        }
        else {
            System.out.println("Nothing found");
        }
    }

    public static boolean GT3(int num) {
        return num > 3;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static double doDouble(int n) {
        return 2.0 * n;
    }
}
