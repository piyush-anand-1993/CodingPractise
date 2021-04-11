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

public class CountOfRangeSumv2 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int sol = 0;
        long currentSum = 0;
        TreeNode root = new TreeNode(0);
        for(int num: nums) {
            currentSum = currentSum + num;
            int low = getNoOfValuesLessThan(root, currentSum - upper);
            int high = getNoOfValuesLessThan(root, currentSum - lower + 1);
            sol  = sol + (high - low);
            insert(root, currentSum);
        }
        return sol;
    }

    private int getNoOfValuesLessThan(TreeNode root, long value) {
        if(root == null) {
            return 0;
        }
        if(root.value >= value)
            return getNoOfValuesLessThan(root.left, value);
        return root.count + root.nodesOnTheLeft + getNoOfValuesLessThan(root.right, value);
    }

    private TreeNode insert(TreeNode root, long value) {
        if(root == null) {
            return new TreeNode(value);
        }

        if(root.value > value) {
            root.nodesOnTheLeft++;
            root.left = insert(root.left, value);
        }
        else if(root.value < value) {
            root.right = insert(root.right, value);
        }
        else if(root.value == value) {
            root.count++;
        }
        return root;
    }

    private class TreeNode {
        public long value;
        public int count;
        public int nodesOnTheLeft;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(long value) {
            this.value = value;
            count = 1;
            nodesOnTheLeft = 0;
        }
    }

    public static void main(String[] args) {
        CountOfRangeSumv2 obj = new CountOfRangeSumv2();
        int[] arr = {-1, -1, -1};
        System.out.println(obj.countRangeSum(arr, -3, 2));

        int[] arr2 = {-2, 5, -1};
        System.out.println(obj.countRangeSum(arr2, -2, 2));

        int[] arr3 = {-2,5,-1,6,7,-5};
        System.out.println(obj.countRangeSum(arr3, -2, 3));
    }

}
