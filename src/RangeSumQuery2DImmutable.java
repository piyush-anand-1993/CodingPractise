/*
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2DImmutable {

    int arr[][];
    int recSum[][];

    public RangeSumQuery2DImmutable(int[][] matrix) {
        arr = matrix;
        recSum = new int[matrix.length][matrix[0].length];

        //init rectangle Sum matrix
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                int sum = arr[i][j];
                int up = (i > 0) ? recSum[i-1][j] : 0;
                int left = (j > 0) ? recSum[i][j-1] : 0;
                int dig = ((i > 0) && (j > 0)) ? recSum[i-1][j-1] : 0;
                recSum[i][j] = arr[i][j] + up + left - dig;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int up = (row1 > 0) ? recSum[row1-1][col2] : 0;
        int left = (col1 > 0) ? recSum[row2][col1-1] : 0;
        int dig = ((row1 > 0) && (col1 > 0)) ? recSum[row1-1][col1-1] : 0;

        int sum = recSum[row2][col2] - up - left + dig;
        return sum;
    }
}
