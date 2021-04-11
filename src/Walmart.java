import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 1 1 1 0 1 1
 * 1 1 0 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 1 1
 *
 *
 * start = 0, 0
 * end = m,n
 *
 *-1 0 1
 *2 0 1
 *3 0 1
 *1 1 1
 *
 * findMinCost(A[][], m, n ) -->
 */

public class Walmart {

    private int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private int R;
    private int C;

    public int findMinCost(int A[][], int m, int n) {

        R=A.length;
        C=A[0].length;

        boolean[][] visited = new boolean[R][C];

        Queue<Point1> q = new ArrayDeque<>();
        q.offer(new Point1(0, 0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Point1 curr = q.poll();

            int len = curr.len;
            if(curr.r == m && curr.c == n) {
                return len;
            }

            for(int i=0; i<8; i++) {
                int nextR = curr.r + dir[i][0];
                int nextC = curr.c + dir[i][1];
                if(isValid(nextR, nextC) && A[nextR][nextC] != 0 && !visited[nextR][nextC]) {
                    q.offer(new Point1(nextR, nextC, len + 1));
                    visited[nextR][nextC] = true;
                }
            }
        }

        return -1;
    }

    private boolean isValid(int r, int c) {
        if(r < 0 || c < 0 || r >= R || c >= C)
            return false;
        return true;
    }

    public static void main(String[] args) {

        /*int arr[][] = { {1, 1, 1, 0, 1, 1},
                          {1, 1, 0, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1}};*/

        int arr[][] = { {1, 0, 1},
                        {1, 0, 1},
                        {1, 0, 1},
                        {1, 1, 1}};

        Walmart obj = new Walmart();
        System.out.println(obj.findMinCost(arr, 0, 2));
    }
}

class Point1 {
    int r; int c;
    int len;
    public  Point1(int r, int c, int len) {
        this.r = r;
        this.c = c;
        this.len = len;
    }

    @Override
    public String toString() {
        return r + " " + c;
    }
}