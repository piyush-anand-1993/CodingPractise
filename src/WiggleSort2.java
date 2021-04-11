import java.util.Arrays;

/**
 * https://leetcode.com/problems/wiggle-sort-ii/
 *
 */
public class WiggleSort2 {
    public int pivot(int arr[], int low, int high) {
        if(low == high)
            return low;
        int pivot = arr[high];
        int i=low-1;
        for(int j=low; j<high; j++) {
            if(arr[j] < pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = tmp;
        return i+1;
    }

    public void wiggleSort(int[] nums) {
        if(nums.length == 0 || nums.length == 1)
            return;
        int mid = (nums.length + 1) / 2;
        int low = 0;
        int high = nums.length -1;
        while(true) {
            int curr = pivot(nums, low, high);
            if(mid < curr) {
                high = curr-1;
            }
            else if (mid >  curr) {
                low = curr + 1;
            }
            else break;
        }

        int n = nums.length;
        int[] temp = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[mid]) {
                temp[left] = nums[i];
                left++;
            } else if (nums[i] > nums[mid]) {
                temp[right] = nums[i];
                right--;
            }
        }

        // add median into the middle
        for (int i = left; i <= right; i++) {
            temp[i] = nums[mid];
        }

        // Step 3: wiggle sort
        left = mid - 1;
        right = n - 1;

        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                nums[i] = temp[left];
                left--;
            } else {
                nums[i] = temp[right];
                right--;
            }
        }

    }

    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        Arrays.sort(nums);
        int n = nums.length;

        int[] temp = new int[n];
        int left = (n - 1) / 2;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                temp[i] = nums[left];
                left--;
            } else {
                temp[i] = nums[right];
                right--;
            }
        }

        System.arraycopy(temp, 0, nums, 0, n);
    }

    public static void main(String[] args) {
        WiggleSort2 obj = new WiggleSort2();
        //int arr[] = {1,2,2,1,2,1,1,1,1,2,2,2};
        int arr[] = {4,5,5,6};
        obj.wiggleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
