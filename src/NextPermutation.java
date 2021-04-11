import java.util.*;

/**
 * https://leetcode.com/problems/next-permutation/
 */

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        //PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(o -> ((Integer) nums[o])));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int largestToRight[] = new int[nums.length];
        int currLargest = -1;
        for(int i=nums.length-1; i>=0; i--) {
            largestToRight[i] = currLargest;
            if(currLargest < nums[i])
                currLargest = nums[i];
        }

        pq.add(nums[nums.length-1]);
        for(int i=nums.length-2; i>=0; i--) {
            if(largestToRight[i] <= nums[i]) {
                pq.add(nums[i]);
                continue;
            }
            int j = i + 1;
            boolean isPosFound = false;
            while (!pq.isEmpty()) {
                int top = pq.peek();
                if(nums[i] < top && !isPosFound) {
                    nums[j] = nums[i];
                    nums[i] = top;
                    isPosFound = true;
                }
                else {
                    nums[j] = top;
                }
                pq.poll();
                j++;
            }
            return;
        }
        // we have reached here meaning we have a descending order list, reverse the list
        int low = 0;
        int high = nums.length -1;
        while(low < high) {
            swap(nums, low, high);
            low++; high--;
        }
    }

    public void swap(int arr[], int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static void main(String args[]) {
        NextPermutation nextPermutation = new NextPermutation();
        //int nums[] = {1,3,2};
        //int nums[] = {1,1,2};
        int nums[] = {3,2,1};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}

