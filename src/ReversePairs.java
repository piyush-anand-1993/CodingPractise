/*
    *https://leetcode.com/problems/reverse-pairs/
    *
    * This solution is not working, would have been working for all +ve numbers but the -ve are causing problem
 */

import java.util.LinkedList;
import java.util.List;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        List<Integer> sol = new LinkedList<>();

        if(nums != null && nums.length > 0) {
            sol.add(0, 0);
            TreeNode2 root = new TreeNode2(nums[nums.length -1]);

            int[] tmp = new int[1];
            for(int i=nums.length-2; i>=0; i--) {
                tmp[0] = 0;
                //sol.add(0, nodeLessThanTwice(root, nums[i]));
                root = insert(root, nums[i], tmp);
                sol.add(0, tmp[0]);
            }
        }

        System.out.println(sol.toString());
        return sol.stream().mapToInt(Integer::intValue).sum();
    }

    private TreeNode2 insert(TreeNode2 root, long value, int[] tmp) {
        if(root == null) {
            return new TreeNode2(value);
        }

        boolean flag = false;
        if(2 * root.value <  value) {
            flag = true;
            tmp[0] += (root.nodesToLeft + root.count);
        }
        if(root.value > value) {
            //correction for -ve numbers
            if (flag) {
                tmp[0] -= root.nodesToLeft;
            }
            //correction ends
            root.nodesToLeft++;
            root.left = insert(root.left, value, tmp);
        }
        else if(root.value < value) {
            if(!(2 * root.value <  value)) {
                tmp[0] += nodeLessThanTwice(root.left, value);
            }
            root.right = insert(root.right, value, tmp);
        }
        else if(root.value == value) {
            root.count++;
            tmp[0] += nodeLessThanTwice(root.left, value);
        }
        return root;
    }

    private int nodeLessThanTwice(TreeNode2 root, long value) {
        if(root == null) {
            return 0;
        }
        if(2 * root.value <  value) {
            int temp = nodeLessThanTwice(root.right, value);
            return temp + root.nodesToLeft + root.count;
        }
        return nodeLessThanTwice(root.left, value);
    }

    public static void main(String[] args) {
        ReversePairs obj = new ReversePairs();
/*
        int[] arr1 = {1,3,2,3,1};
        //2
        int[] arr2 = {2,4,3,5,1};
        //3
        int[] arr3 = {-5,-5};
        //1
        System.out.println(obj.reversePairs(arr1));
        System.out.println(obj.reversePairs(arr2));
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
*/

        int[] arr9 = {-364,-22,-5,9,-212,-315};
        //9
        System.out.println(obj.reversePairs(arr9));
    }
}

class TreeNode2 {
    public TreeNode2 left;
    public TreeNode2 right;
    public long value;
    public int count;
    public int nodesToLeft;

    public TreeNode2(long value) {
        this.value = value;
        count = 1;
        nodesToLeft = 0;
    }
}

