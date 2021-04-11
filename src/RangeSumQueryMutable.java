/*
 * https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {

    int nums[];
    int sumArr[];

    public RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        if(nums.length == 0)
            return;
        sumArr = new int[nums.length];
        int currSum = 0;
        for(int i=0; i< nums.length; i++) {
            currSum = currSum + nums[i];
            sumArr[i] = currSum;
        }
    }

    public void update(int i, int val) {
        if(nums.length == 0)
            return;
        int delta = val - nums[i];
        nums[i] = val;
        for(;i < nums.length; i++) {
            sumArr[i] = sumArr[i] + delta;
        }
    }

    public int sumRange(int i, int j) {
        if(nums.length == 0)
            return 0;
        int left = i > 0 ? sumArr[i-1] : 0;
        return sumArr[j] - left;
    }
}
