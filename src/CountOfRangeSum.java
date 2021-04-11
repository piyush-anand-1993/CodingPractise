import java.util.*;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 *
 * Solution:
 * The problem is counting the number of lower <= sum([a,b]) <= upper.
 *
 * My idea of solving this problem is using prefix sum to quick calculate sum of subarray [a,b].
 * sum([a,b]) =val[b] + prefixSum[b] - prefixSum[a]. Which means:
 *
 * lower <= prefixSum[b] - prefixSum[a] <= upper.
 * In other words, given current index b, find how many index a (0<=a<b) that satisfy the condition:
 *
 * val[b] + prefixSum[b] - upper <= prefixSum[a] <= val[b] + prefixSum[b] - lower
 * sum[b] - upper <= prefix[a] <= sum[b] - lower
 */
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long runningSum = 0;
        int count = 0 ;
        TreeMap<Long,Integer> map = new TreeMap();
        map.put(0l,1);
        for(int ele : nums){
            runningSum = runningSum + ele;
            count = count + getCount(map.subMap(runningSum - upper,true, runningSum-lower, true));
            map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);
        }
        return count;
    }

    public int getCount(Map<Long,Integer> map){
        if(map.isEmpty()) return 0;
        int count = 0;
        return map.values().stream().mapToInt(Integer::intValue).sum();
/*        for(int value : map.values()){
            count = count + value;
        }
        return count;*/
    }

    public static void main(String[] args) {
        CountOfRangeSum obj = new CountOfRangeSum();
        int arr[] = {-1, -1, -1};
        System.out.println(obj.countRangeSum(arr, -3, 2));
    }
}
