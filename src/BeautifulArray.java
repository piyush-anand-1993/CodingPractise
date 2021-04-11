import java.util.Arrays;

/**
 * https://leetcode.com/problems/beautiful-array/
 */
public class BeautifulArray {
    public int[] beautifulArray(int N) {
        int[] arr = new int[N];
        recur(N, arr, 0, N-1);
        return arr;
    }

    private void recur(int N, int[] arr, int low, int high) {
        //System.out.println(low + "\t" + high);
        if(low == high) {
            arr[low] = N;
            return;
        }

        int nOdd = (N+1)/2;
        int nEven = (N)/2;

        recur(nOdd, arr, low, low+nOdd-1);
        //int currOdd = nOdd*2 -1;
        for(int i=low; i<(low+nOdd) && i<= high; i++) {
            arr[i] = arr[i]*2;
            arr[i] = arr[i]-1;
        }
        recur(nEven, arr, low+nOdd, high);
        for(int i=low+nOdd;i<=high;i++) {
            arr[i] = arr[i] * 2;
        }
    }
    public static void main(String[] args) {
        BeautifulArray obj = new BeautifulArray();
        System.out.println(Arrays.toString(obj.beautifulArray(13)));
    }
}

//1 3 5 7 9

//1 7
//1 3 5 7 2 4 6

