import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CountSmallerNoAfterSelfv2 {

    public List<Integer> countSmaller(int[] nums) {
        Integer[] sol = new Integer[nums.length];
        Item[] items = new Item[nums.length];
        for(int i=0; i<nums.length; i++) {
            items[i] = new Item(nums[i], i);
            sol[i] = 0;
        }

        mergeSort(items, 0, items.length - 1, sol);

        return Arrays.asList(sol);
    }

    public void mergeSort(Item[] items, int low, int high, Integer[] sol) {
        if(low < high) {
            int mid = (low + high)/2;
            mergeSort(items, low, mid, sol);
            mergeSort(items, mid+1, high, sol);
            merge(items, low, mid, high, sol);
        }
    }

    public void merge(Item[] items, int low, int mid, int high, Integer[] sol) {
        Item[] temp = new Item[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        int rightCounter = 0;

        while(i <= mid && j <= high) {
            if(items[i].value <= items[j].value) {
                sol[items[i].pos] += rightCounter;
                temp[k++] = items[i++];
            }
            else {
                rightCounter++;
                temp[k++] = items[j++];
            }
        }

        while(i <= mid) {
            sol[items[i].pos] += rightCounter;
            temp[k++] = items[i++];
        }

        while(j<=high) {
            temp[k++] = items[j++];
        }

        k=0;
        for(int m=low; m<=high; m++) {
            items[m] = temp[k++];
        }
    }

    public static void main(String[] args) {
        CountSmallerNoAfterSelfv2 obj = new CountSmallerNoAfterSelfv2();
        int[] arr = {5,2,6,1};
        List<Integer> sol = obj.countSmaller(arr);
        System.out.println(sol.toString());
    }
}

class Item {
    public long value;
    public int pos;

    public Item(long value, int pos) {
        this.value = value;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
