/**
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/
 */

public class MinimumCostToMergeStones {
    int[][] dp;
    public int mergeStones(int[] stones, int K) {
        if(stones == null || stones.length == 0) {
            return 0;
        }
        if(((stones.length - 1) % (K - 1)) != 0) {
            return -1;
        }
        dp = new int[stones.length][stones.length];

        return recur(stones, 0, stones.length-1,K);
    }

    private int recur(int[] stones, int low, int high, int K) {
        if(low >= stones.length)
            return 0;
        if(dp[low][high] > 0) {
            return dp[low][high];
        }

        int min = Integer.MAX_VALUE;
        int currSum = 0;
        for(int i=0; i<K && (low + i < stones.length); i++) {
            currSum = currSum + stones[low + i];
            min = Math.min(min, currSum + recur(stones, low + i + 1, high, K));
        }
        dp[low][high] = min;
        return dp[low][high];
    }

    public static void main(String[] args) {
        MinimumCostToMergeStones obj = new MinimumCostToMergeStones();

        int arr[] = {3,2,4,1};
        System.out.println(obj.mergeStones(arr, 2));

    }
}
