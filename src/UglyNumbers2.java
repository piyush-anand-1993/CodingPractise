import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://leetcode.com/problems/ugly-number-ii/
 */
public class UglyNumbers2 {
    public int nthUglyNumber(int n) {
        if(n == 0)
            return 0;
        long arr[] = new long[n];
        arr[0] = 1;
        for(int i=1; i<n; i++) {
            long nextmax = Long.MAX_VALUE;
            for(int j=i-1; j>=0; j--) {
                //long m2 = Long.MAX_VALUE;
                //long m3 = Long.MAX_VALUE;
                //long m5 = Long.MAX_VALUE;
                long m = Long.MAX_VALUE;
                if(arr[i-1] < arr[j] * 2)
                    m = arr[j] * 2;
                else if(arr[i-1] < arr[j] * 3)
                    m = arr[j] * 3;
                else if(arr[i-1] < arr[j] * 5)
                    m = arr[j] * 5;
                else break;

                //long temp = min3(m2, m3, m5);
                long temp = m;
                if(temp > arr[i-1] && nextmax > temp) {
                    nextmax = temp;
                }
            }
            arr[i] = nextmax;
        }
        //System.out.println(Arrays.toString(arr));
        return (int) arr[n-1];
    }

    public long min3(long a, long b, long c){
        if(a <= b && a <= c) return a;
        if(b <= a && b <= c) return b;
        return c;
    }

    public int nthUglyNumber_v2(int n) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(1L);
        int sol = 0;
        long max = Long.MIN_VALUE;
        while(!q.isEmpty()) {
            long curr = q.poll();
            while(!q.isEmpty() && curr == q.peek())
                curr = q.poll();
            //System.out.print(curr + " ");
            if(curr > max)
                max = curr;
            sol = (int)curr;
            long m2 = curr * 2L; long m3 = curr * 3L; long m5 = curr * 5L;
            if(m2 > max) q.add(m2);
            if(m3 > max) q.add(m3);
            if(m5 > max) q.add(m5);
            if(--n == 0)
                break;
        }
        return sol;
    }
}
