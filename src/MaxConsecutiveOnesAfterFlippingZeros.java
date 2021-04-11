/*
https://leetcode.com/problems/max-consecutive-ones-iii/#
 */

import java.util.*;

public class MaxConsecutiveOnesAfterFlippingZeros {
    public int longestOnes(int[] A, int K) {
        Queue<Integer> queue = new ArrayDeque<>();
        int maxLen = 0;
        int currLen = 0;

        for(int i=0; i<A.length; i++) {
            if(A[i] == 1) {
                currLen++;
            }
            else {
                if(K > 0) {
                    queue.add(i);
                    currLen++;
                    K--;
                }
                else if(!queue.isEmpty()) {
                    int firstPos = queue.poll();
                    currLen = i - firstPos;
                    queue.add(i);
                }
                else {
                    currLen = 0;
                }
            }

            if(currLen > maxLen) {
                maxLen = currLen;
            }
        }
        return maxLen;
    }
}
