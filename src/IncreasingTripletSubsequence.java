import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;

        int[] minToLeft = new int[nums.length];
        int[] maxToRight = new int[nums.length];

        minToLeft[0] = Integer.MAX_VALUE;
        int minTillNow = nums[0];
        for(int i= 1; i<nums.length; i++) {
            minToLeft[i] = minTillNow;
            if(nums[i] < minTillNow) minTillNow = nums[i];
        }

        maxToRight[nums.length -1] = Integer.MIN_VALUE;
        int maxTillNow = nums[nums.length -1];
        for(int i= nums.length - 2; i>-1; i--) {
            maxToRight[i] = maxTillNow;
            if(nums[i] > maxTillNow) maxTillNow = nums[i];
        }

        for(int i =1 ; i< nums.length -1; i++) {
            if(nums[i] > minToLeft[i] && nums[i] < maxToRight[i]) return true;
        }
        return false;
    }

}
