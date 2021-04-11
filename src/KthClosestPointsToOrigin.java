import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KthClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {

        //creating min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[0]*o2[0] + o2[1]*o2[1]) - (o1[0]*o1[0] + o1[1]*o1[1]));

        int i=0;
        for(;i<K && i<points.length; i++) {
            pq.add(points[i]);
        }
        for(;i<points.length; i++) {
            pq.add(points[i]);
            System.out.println("Poll: " + Arrays.toString(pq.poll()));
        }

        return pq.toArray(new int[pq.size()][]);
    }

    public static void main(String[] args) {
        KthClosestPointsToOrigin obj =  new KthClosestPointsToOrigin();
        int[][] arr = {{3,3},{5,-1},{-2,4}};
        System.out.println(Arrays.deepToString(obj.kClosest(arr, 1)));
    }
}
