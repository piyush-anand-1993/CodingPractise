package JavaSample;

import java.util.List;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class Sample11 {

    private static boolean isEven(int n) {
        return n%2==0;
    }

    public static void notPredicateUse() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        //Java 8 has negate function on predicate but then we need to cast the lambda to predicate
        numbers.stream()
                .filter(((Predicate<? super Integer>) (Sample11::isEven)).negate())
                .forEach(System.out::println);
        System.out.println("----------------------");
        //Java 11 now has not function (static in function -> predicate)
        numbers.stream()
                .filter(not(Sample11::isEven))
                .forEach(System.out::println);
    }

    public static void switchEpression() {
        //switch is no more a statement but an expression, we can not use it on the right hand side of = and it
        //returns a result
        //no need to write breaks anymore
        //switch works with string also

        String greet = "Hello";

/*        String result = switch(greet) {
            case "Hello" -> "Hi there";
            case "Howdy" -> "Howdy pardner";
            default -> "Something";
        };
        System.out.println(result);*/
    }

    public static void main(String[] args) {
        notPredicateUse();
        //switchExpression();
    }
}
