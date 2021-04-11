import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class ReversePairs2 {

    public int reversePairs(int[] nums) {
        Integer[] sol = new Integer[nums.length];
        Item[] items = new Item[nums.length];
        for(int i=0; i<nums.length; i++) {
            items[i] = new Item(nums[i], i);
            sol[i] = 0;
        }

        mergeSort(items, 0, items.length - 1, sol);

        System.out.println(Arrays.toString(sol));
        return Arrays.asList(sol).stream().mapToInt(Integer::intValue).sum();
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

        while(i <= mid) {
            while(j<= high && items[i].value > 2*items[j].value) {
                j++;
            }
            sol[items[i].pos] += (j - mid - 1);
            i++;
        }

        i = low;
        j = mid + 1;

        while(i <= mid && j <= high) {
            if(items[i].value < items[j].value) {
                temp[k++] = items[i++];
            }
            else {
                temp[k++] = items[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = items[i++];
        }

        while(j<=high) {
            temp[k++] = items[j++];
        }

        k=0;
        for(int m=low; m<=high; m++) {
            items[m] = temp[k++];
        }

        //System.out.println(Arrays.toString(temp));
        //System.out.println(Arrays.toString(sol));
        //System.out.println("~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        ReversePairs2 obj = new ReversePairs2();
        int[] arr1 = {1,3,2,3,1};
        //2
        System.out.println(obj.reversePairs(arr1));

        int[] arr2 = {2,4,3,5,1};
        //3
        System.out.println(obj.reversePairs(arr2));

        int[] arr3 = {-5,-5};
        //1
        System.out.println(obj.reversePairs(arr3));
        int[] arr4 = {5,4,3,2,1};
        //4
        System.out.println(obj.reversePairs(arr4));
        int[] arr5 = {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        //9
        System.out.println(obj.reversePairs(arr5));

        int[] arr6 = {2000000005,233,233,233,233,233,2000000004};
        //5
        System.out.println(obj.reversePairs(arr6));
        int[] arr7 = {2000000001,234,2000000006,235};
        //3
        System.out.println(obj.reversePairs(arr7));

        int[] arr8 = {-5,9,-212,46,-4};
        //5
        System.out.println(obj.reversePairs(arr8));

        int[] arr9 = {-364,-22,-5,9,-212,-315};
        //9
        System.out.println(obj.reversePairs(arr9));
    }
}