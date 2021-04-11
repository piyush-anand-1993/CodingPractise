import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */

public class KPairsWithSmallestSum {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums1.length < 1 || nums2.length < 1) {
            return ans;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        int[] nums1Ptrs = new int[nums1.length];
        for(int i = 0; i < nums1Ptrs.length; i++) {
            pq.offer(new int[]{i, nums1Ptrs[i]});
        }

        while (ans.size() < k && pq.size() > 0) {
            int[] curr = pq.poll();
            ans.add(List.of(nums1[curr[0]], nums2[curr[1]]));
            nums1Ptrs[curr[0]]++;
            if(nums1Ptrs[curr[0]] < nums2.length) {
                pq.offer(new int[]{curr[0], nums1Ptrs[curr[0]]});
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        KPairsWithSmallestSum kp = new KPairsWithSmallestSum();

        int[] arr1 = {1,7,11};
        int[] arr2 = {2,4,6};

        System.out.println(kp.kSmallestPairs(arr1, arr2, 3));
    }
}
