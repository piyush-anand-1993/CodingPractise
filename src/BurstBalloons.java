import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/burst-balloons/
 */

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        int sol = 0;
        int[][] dp = new int[nums.length][nums.length];

        //we'll be travesing diagonally
        //i -> length of the sub array

        for(int i=1; i<=nums.length; i++) {
            for(int left=0; left<=nums.length - i;left++) {
                int right = left + i - 1;
                for(int k=left; k<=right; k++) {
                   int coins = getBalloon(nums, left-1) * getBalloon(nums, right + 1) * getBalloon(nums, k);
                   dp[left][right] = Math.max(dp[left][right], coins + getCoins(dp, left, k-1) + getCoins(dp, k+1, right));
                }
            }
        }
        return dp[0][nums.length-1];
    }

    private int getCoins(int[][] dp, int left , int right) {
        if(left < 0 || left >= dp.length || right < 0 || right >= dp.length) {
            return 0;
        }
        return dp[left][right];
    }

    private int getBalloon(int[] nums, int index) {
        if(index < 0 || index >= nums.length)
            return 1;
        return nums[index];
    }

    public static void main(String[] args) {
        BurstBalloons obj = new BurstBalloons();

        int[] arr = {3, 1, 5, 8};
        System.out.println(obj.maxCoins(arr));

        int[] arr2 = {8,3,4,3,5,0,5,6,6,2,8,5,6,2,3,8,3,5,1,0,2};
        System.out.println(obj.maxCoins(arr2));
    }


}
