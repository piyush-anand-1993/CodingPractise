import java.util.Arrays;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if(nums.length == 0) return true;

        int sum = Arrays.stream(nums).sum();
        //Arrays.sort(nums);

        if(sum%2 != 0) return false;

        boolean arr[][] = new boolean[nums.length+1][sum/2+1];
        arr[0][0] = true;
        for(int i=1; i<sum/2+1; i++) {
            arr[0][i] = false;
        }
        for(int i=1; i<nums.length+1; i++) {
            arr[i][0] = true;
        }
        for(int i=1; i<nums.length+1; i++) {
            for(int j=1; j<sum/2+1; j++) {
                boolean useThis = false;
                if(j - nums[i-1] >= 0) {
                    useThis = arr[i-1][j - nums[i-1]];
                }
                arr[i][j] = useThis || arr[i-1][j];
            }
            // if half the sum reached even without traversing the full array, we are still good
            if(arr[i][sum/2]) return true;
        }
        //System.out.println(Arrays.deepToString(arr));
        return arr[nums.length][sum/2];
    }

    public static void main(String args[]) throws Exception {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int nums[] = {1, 5, 11, 5};
        int nums2[] = {1, 5, 2, 5, 3, 7, 1, 1, 3, 3, 3};
        System.out.println(obj.canPartition(nums));
        System.out.println(obj.canPartition(nums2));
    }
}
