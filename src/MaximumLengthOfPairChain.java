import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
https://leetcode.com/problems/maximum-length-of-pair-chain/
 */
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        System.out.println(Arrays.toString(pairs));
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        return 0;
    }

    public static void main(String[] args) {
        //{{3,4},{2,3},{1,2}}
        int arr[][] = {{5, 9},{3,4},{2,3},{1,2}};
        MaximumLengthOfPairChain obj = new MaximumLengthOfPairChain();
        obj.findLongestChain(arr);
    }
}
