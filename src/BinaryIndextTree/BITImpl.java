package BinaryIndextTree;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BITImpl {

    //assumption: index starting from 1, 0 the index in the array is empty
    int arr[];
    int tree[];

    public BITImpl(int a[]) {
        //we cannot simply assign the array like below because remember how our BIT indexes only start from 1 and not 0
        //this.arr = arr;
        this.arr = new int[a.length + 1];

        //create the BIT tree
        tree = new int[this.arr.length];
        IntStream.range(1, this.arr.length)
                .forEach(o -> update(o, a[o - 1]));
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(tree));
    }

    public void update(int idx, int delta) {
        this.arr[idx] = this.arr[idx] + delta;
        while(idx < tree.length) {
            tree[idx] += delta;
            idx = idx + (idx & -idx);
        }
    }

    //return the cumulative sum from 0 index to idx index of in the arr
    public int getCummSum(int idx) {
        int sum = 0;
        while(idx > 0) {
            sum = sum + tree[idx];
            idx = idx - (idx & -idx);
        }
        return sum;
    }

    public int getValue(int idx) {
        return this.arr[idx];
    }

    public void update_(int idx, int value) {
        int delta = value - getValue(idx + 1);
        update(idx + 1, delta);
    }

    public int sumRange(int i, int j) {
        return getCummSum(j + 1) - getCummSum(i);
    }

    public static void main(String args[]) {
        int arr[] = {1, 3, 5};
        BITImpl imp = new BITImpl(arr);
        System.out.println(imp.sumRange(0, 2));
        imp.update_(1, 2);
        System.out.println(imp.sumRange(0, 2));
    }
}
