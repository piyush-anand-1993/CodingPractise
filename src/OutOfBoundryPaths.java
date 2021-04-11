/**
 * https://leetcode.com/problems/out-of-boundary-paths/
 *
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 */

public class OutOfBoundryPaths {

    long[][][] dp;
    boolean[][][] isTraversed;
    int MOD = 1000000000 + 7;

    public int findPaths(int m, int n, int N, int i, int j) {
        dp = new long[m][n][N+1];
        isTraversed = new boolean[m][n][N+1];
        long sol =  recur(m, n, N, i, j);
        return (int)sol;
    }

    private long recur(int m, int n, int N, int i, int j) {

        //base case -> reached outside one possible way
        if(i<0 || j<0 || i>=m || j>=n) {
            return 1;
        }

        //no possible n moves) remaining, not possible to traverse any further
        if(N==0) {
            return 0;
        }

        if(isTraversed[i][j][N]) {
            return dp[i][j][N];
        }

        long up = recur(m, n, N-1, i-1, j);
        long down = recur(m, n, N-1, i+1, j);
        long left = recur(m, n, N-1, i, j-1);
        long right = recur(m, n, N-1, i, j+1);

        //dp[i][j][N] = up + down + left + right;
        dp[i][j][N] = addAndMod(up, down, left, right);
        isTraversed[i][j][N] = true;

        return dp[i][j][N];
    }

    private long addAndMod(long ...arr) {
        long sum = 0;
        for(long num: arr) {
            sum = (sum + num) % MOD;
        }
        return sum;
    }
}
