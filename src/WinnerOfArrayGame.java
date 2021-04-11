import java.util.*;

/**
 * https://leetcode.com/problems/find-the-winner-of-an-array-game/
 */
public class WinnerOfArrayGame {
    public int getWinner(int[] arr, int k) {
        if(k >= arr.length) {
            //max element of array
            int maX = arr[0];
            for(int i=1; i<arr.length; i++){
                if(maX < arr[i])
                    maX = arr[i];
            }
            return maX;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int max = arr[0];
        for(int num: arr) {
            deque.add(num);
            if(max < num) {
                max = num;
            }
        }

        int curr =0;
        while (curr != k) {
            int top = deque.pollFirst();
            int top2 = deque.pollFirst();

            if(top > top2) {
                deque.offerFirst(top);
                deque.offerLast(top2);
                curr++;
            }
            else {
                deque.offerFirst(top2);
                deque.offerLast(top);
                curr=1;
            }
            if(deque.peek() == max) {
                return max;
            }
        }
        return deque.pollFirst();
    }
}
