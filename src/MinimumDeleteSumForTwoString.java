/*
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class MinimumDeleteSumForTwoString {

    int[][] dp = new int[1001][1001];

    public int minimumDeleteSum(String s1, String s2) {
        for(int i=0; i<1001; i++) {
            for(int j=0; j<1001; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return recur(new String(s1), 0, new String(s2), 0);
    }

    public int recur(String s1, int idx1, String s2, int idx2) {
        if((s1.isEmpty() && s2.isEmpty()) || (s1.length() == idx1 && s2.length() == idx2)) {
            return 0;
        }

        if(dp[idx1][idx2] != Integer.MAX_VALUE)
            return dp[idx1][idx2];

        int case1 = Integer.MAX_VALUE;
        int case2 = Integer.MAX_VALUE;
        int case3 = Integer.MAX_VALUE;
        if(!s1.isEmpty() && !s2.isEmpty() && idx1 < s1.length() && idx2 < s2.length() && s1.charAt(idx1) == s2.charAt(idx2)) {
            case1 = recur(s1, idx1 + 1, s2, idx2 + 1);
        }
        if(!s1.isEmpty() && idx1 < s1.length())
            case2 = (int) s1.charAt(idx1) + recur(s1, idx1 + 1, s2, idx2);

        if(!s2.isEmpty() && idx2 < s2.length())
            case3 = (int) s2.charAt(idx2) + recur(s1, idx1 , s2, idx2 + 1);
        int sol = Math.min(case1, Math.min(case2,case3));
        if(dp[idx1][idx2] > sol) dp[idx1][idx2] = sol;
        return sol;
    }

    /*
    public boolean stringIsEmpty(String str) {
        return str == null || str.isEmpty();
    }

     */

    public static void main(String[] args) {
        MinimumDeleteSumForTwoString obj = new MinimumDeleteSumForTwoString();
        System.out.println(obj.minimumDeleteSum("sea","eat"));
        System.out.println(obj.minimumDeleteSum("delete", "leet"));
    }
}
