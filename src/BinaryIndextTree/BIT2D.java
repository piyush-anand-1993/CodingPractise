package BinaryIndextTree;

public class BIT2D {

    public int arr[][];
    public int tree[][];

    public BIT2D(int nums[][]) {
        if(nums.length == 0)
            return;
        arr = new int[nums.length + 1][nums[0].length + 1];
        tree = new int[nums.length + 1][nums[0].length + 1];

        for(int i=1; i<tree.length; i++) {
            for(int j=1; j<tree[0].length; j++) {
                update(i, j, nums[i-1][j-1]);
                System.out.print("\t" + arr[i][j]);
            }
            System.out.println();
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=1; i<tree.length; i++) {
            for(int j=1; j<tree[0].length; j++) {
                System.out.print("\t" + tree[i][j]);
            }
            System.out.println();
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
     }

    public void update(int i, int j, int delta) {
        arr[i][j] = arr[i][j] + delta;
        while (i < tree.length) {
            updateY(i, j, delta);
            i = i + (i & -i);
        }
    }

    public void updateY(int i, int j, int delta) {
        while(j < tree[0].length) {
            tree[i][j] = tree[i][j] + delta;
            j = j + (j & -j);
        }
    }

    public int getCummSum(int i, int j) {
        int sum = 0;
        while(i > 0) {
            sum = sum + getCummSumY(i, j);
            i = i - (i & -i);
        }
        return sum;
    }

    private int getCummSumY(int i, int j) {
        int ySum = 0;
        while(j > 0) {
            ySum = ySum + tree[i][j];
            j = j - (j & -j);
        }
        return ySum;
    }

    public int getValue(int i, int j) {
        return arr[i][j];
    }

    public static void main(String[] args) {
        int arr[][] = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix obj = new NumMatrix(arr);

        System.out.println(obj.sumRegion(2,1, 4, 3));
        obj.update(3,2, 2);
        System.out.println(obj.sumRegion(2,1, 4, 3));
    }
}


class NumMatrix {

    BIT2D bit;
    public NumMatrix(int[][] matrix) {
        bit = new BIT2D(matrix);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int tot = bit.getCummSum(row2 + 1, col2 + 1);
        int up = bit.getCummSum(row1, col2 + 1);
        int left = bit.getCummSum(row2 + 1, col1);
        int dig = bit.getCummSum(row1, col1);

        System.out.println("total: " + tot + "\tup: " + up + "\tleft: " + left + "\tdig: " + dig);
        return tot - up - left + dig;
    }

    public void update(int i, int j , int val) {
        //bit.update(i + 1, j + 1, val - bit.getValue(i + 1, j + 1));
        bit.update(i + 1, j + 1, val - bit.arr[i][j]);
    }
}

