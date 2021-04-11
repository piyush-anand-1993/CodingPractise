/*
https://leetcode.com/problems/reveal-cards-in-increasing-order/
 */

import java.util.*;

public class RevealCardsIncreasingOrder {
    public int[] deckRevealedIncreasing(int[] deck) {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i =0; i<deck.length; i++) {
            q.add(i);
        }
        Arrays.sort(deck);
        int[] sol = new int[deck.length];
        for (int value : deck) {
            if(q.peek() != null) {
                int top = q.poll();
                sol[top] = value;
            }
            if(q.peek() != null) {
                int top_minus_1 = q.poll();
                q.add(top_minus_1);
            }
        }
        return sol;
    }
}
