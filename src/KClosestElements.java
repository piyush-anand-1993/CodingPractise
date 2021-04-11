import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.lintcode.com/problem/find-k-closest-elements/
 */

public class KClosestElements {

    public int[] kClosestNumbers(int[] A, int target, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int n1 = Math.abs(o1 - target);
            int n2 = Math.abs(o2 - target);
            if(n1 == n2)
                return o2 - o1;
            return n2 - n1;
        });

        int i = 0 ;

        for(; i<k; i++) {
            queue.offer(A[i]);
        }

        for(;i<A.length; i++) {
            queue.offer(A[i]);
            queue.poll();
        }

        int[] sol = new int[k];
        for(i=k-1;i>=0; i--) {
            sol[i] = queue.poll();
        }

        return sol;
    }

    public static void main(String args[]) {
        KClosestElements o = new KClosestElements();
        System.out.println(Arrays.toString(o.kClosestNumbers(new int[]{1,2,3}, 2, 3)));
    }
}
