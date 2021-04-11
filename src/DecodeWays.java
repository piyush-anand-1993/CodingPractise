/**
 * https://leetcode.com/problems/decode-ways/
 */


public class DecodeWays {
    int[] dp;
    public int numDecodings(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        dp = new int[s.length()];
        return recur(s, 0);
    }

    public int recur(String s, int low) {
        int high = s.length()-1;
        if(low > high) {
            return 1;
        }
        if(s.charAt(low) == '0') {
            return 0;
        }
        if(dp[low] > 0) {
            return dp[low];
        }
        if(low == high) {
            dp[low]= 1;
            return dp[low];
        }

        int noOfWays = recur(s, low+1);

        //number s[low]s[low+1] is a between (both included) 'A' & 'Z'
        //String str = "" + s.charAt(low) + s.charAt(low+1);
        //int val = Integer.parseInt(str);
        int val = (s.charAt(low) - '0') * 10 + (s.charAt(low+1)-'0');
        if(val >=1 && val <=26) {
            noOfWays += recur(s, low+2);
        }
        dp[low] = noOfWays;
        return dp[low];
    }
}
