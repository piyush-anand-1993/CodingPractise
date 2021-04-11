/*
https://leetcode.com/problems/longest-repeating-character-replacement/
 */

import java.util.ArrayDeque;
import java.util.*;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {

        int maxLen = 0;
        int len = s.length();
        Queue<Integer> queue = new ArrayDeque<>();

        Set<Character> characterSet = new HashSet<>();
        for(int i=0; i<len; i++) {
            characterSet.add(s.charAt(i));
        }

        for(char j : characterSet) {
            queue.clear();
            int currLen = 0;
            int K=k;

            for(int i=0; i<len; i++) {
                if(s.charAt(i) == j) {
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
        }
        return maxLen;
    }
}
