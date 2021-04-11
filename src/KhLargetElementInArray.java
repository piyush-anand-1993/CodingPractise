import java.util.Arrays;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * this can be quickly implemented using k size min help pq
 * but well go ahead and use quick sort
 */
public class KhLargetElementInArray {

    public int findKthLargest(int[] nums, int k) {
        int sol = quick(nums, 0, nums.length-1, nums.length-k+1);

        //System.out.println("Requested: " + (nums.length-k+1));
        //System.out.println("Position: " + pos);
        return sol;
    }


    private int quick(int[] num, int low, int high, int k) {
        if(low < high) {
            int pivot = pivot(num, low, high);
            if(pivot > (k-1)) {
                return quick(num, low, pivot-1, k);
            }
            else if(pivot < (k-1)) {
                return quick(num, pivot+1, high, k);
            }
            return num[pivot];
        }
        return (low == high) && (low == (k-1)) ? num[low] : -1;
    }

    private int pivot(int[] num, int low, int high) {
        if(low == high)
            return low;

        int curr = num[high];
        int idx1 = low;

        while(low < high) {
            if(num[low] < curr) {
                //swap
                int temp = num[low];
                num[low] = num[idx1];
                num[idx1] = temp;
                idx1++;
            }
            low++;
        }

        int temp = num[high];
        num[high] = num[idx1];
        num[idx1] = temp;

        return idx1;
    }

    public static void main(String[] args) {
        KhLargetElementInArray obj = new KhLargetElementInArray();
        int arr[] = {3,2,3,1,2,4,5,5,6};
        System.out.println(obj.findKthLargest(arr, 6));
        System.out.println(Arrays.toString(arr));
    }
}
