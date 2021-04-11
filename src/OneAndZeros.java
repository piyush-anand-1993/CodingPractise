/**
 * https://leetcode.com/problems/ones-and-zeroes/
 */
public class OneAndZeros {

    private int[][][] dp = new int[0][0][0];

    public int findMaxForm(String[] strs, int m, int n) {
        dp = new int[601][101][101];
        return Math.max(0, recur(strs, m, n, 0));
    }

    private int recur(String[] strs, int m, int n, int idx) {

        if(m < 0 || n < 0) {
            return Integer.MIN_VALUE;
        }

        if(dp[idx][m][n] > 0) {
            return dp[idx][m][n];
        }

        if(idx == strs.length) {
            return 0;
        }

        //do not select the current element
        int a = recur(strs, m, n, idx+1);

        //select the current element
        int b = recur(strs, m - freq(strs[idx], '0'), n - freq(strs[idx], '1'), idx+1);

        dp[idx][m][n] = Math.max(a, b+1);

        return dp[idx][m][n];
    }

    int freq(String s, char c) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
}
