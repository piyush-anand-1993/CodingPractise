import java.util.Arrays;

/*
 *  https://leetcode.com/problems/sum-of-subsequence-widths/
 */
public class SumOfSubsequenceWidths {

    private int MOD = 1000000000 + 7;

    public int sumSubseqWidths(int[] A) {
        int[] sum = new int[1];
        Arrays.sort(A);
        recur(A, 0, sum, 0, 0, false);
        return sum[0];
    }

    private void recur(int[] arr, int idx, int[] sum, int first, int last, boolean firstFlag) {
        if(idx >= arr.length) {
            //System.out.println(first + "\t" + last);
            int diff = last - first;
            sum[0] = (sum[0] + diff) % MOD;
            return;
        }
        //do not take the current element
        recur(arr, idx + 1, sum, first, last, firstFlag);
        //take the current element
        if(!firstFlag) {
            firstFlag = true;
            first = arr[idx];
        }
        last = arr[idx];
        recur(arr, idx + 1, sum, first, last, firstFlag);
    }

    public int sumSubseqWidths_v2(int[] A) {
        long sum = 0;
        Arrays.sort(A);

        long[] pow2 = new long[A.length];
        pow2[0] = 1;
        for(int i=1; i<A.length; i++) {
            pow2[i] = (pow2[i-1] * 2) % MOD;
        }

        for(int i=0; i<A.length; i++) {
            for(int j=i+1; j< A.length; j++) {
                int no_in_between = j - i -1;
                sum = (sum + ((A[j] - A[i]) * pow2[no_in_between]) % MOD) % MOD;
            }
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        SumOfSubsequenceWidths obj = new SumOfSubsequenceWidths();
        int[] arr = {1, 2, 4};
        System.out.println(obj.sumSubseqWidths_v2(arr));
    }
}
