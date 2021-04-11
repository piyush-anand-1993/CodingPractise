import java.util.*;

public class Tester {

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1,2,3);
        List<Integer> l2 = Arrays.asList(3,2,1);
        List<Integer> l3 = Arrays.asList(1,2,3);
        List<Integer> l4 = Arrays.asList(1,2,2);
        List<Integer> l5 = Arrays.asList(2,2,1);
        List<Integer> l6 = Arrays.asList(2,2,1);


        Set<List<Integer>> set = new HashSet<>();
        set.add(l1);
        set.add(l2);
        set.add(l3);
        set.add(l4);
        set.add(l5);
        set.add(l6);

        System.out.println(set.size());
        System.out.println(set.toString());

        List<Travel> ll1 = new ArrayList<>();
        Collections.sort(ll1, (o1, o2) -> {
            if(o1.pos >= o2.pos) {
                if(o1.time <= o2.time) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else if(o1.pos < o2.pos) {
                if(o1.time > o2.time) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            return 0;
        });
    }

    class Travel {
        int pos;
        int speed;
        int time;
        int adjTime;

        Travel(int pos, int speed, int target) {
            this.pos = pos;
            this.speed = speed;
            this.time = (int)Math.ceil(((double)(target - pos)) / speed);
        }
    }
}
