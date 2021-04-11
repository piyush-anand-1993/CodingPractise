import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestNoInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if(k <= 0 || matrix.length <=0)
            return 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(o -> matrix[o.y][o.x]));

        for(int i=0; i< matrix[0].length; i++) {
            pq.add(new Pair( 0, i));
        }

        Pair pair = pq.peek();
        while(k-- > 0) {
            pair = pq.poll();
            if(pair.y < matrix.length - 1) {
                pq.add(new Pair(pair.y + 1, pair.x));
            }
        }
        return matrix[pair.y][pair.x];
    }

    private class Pair {
        int x;
        int y;
        public Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        KthSmallestNoInSortedMatrix obj = new KthSmallestNoInSortedMatrix();
        int arr[][] =  {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(obj.kthSmallest(arr,8));
    }
}
