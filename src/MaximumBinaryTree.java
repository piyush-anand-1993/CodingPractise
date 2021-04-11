/*
https://leetcode.com/problems/maximum-binary-tree/
 */

//ranged query since find max on left and right hand side, must be solved using a segment tree

import com.sun.source.tree.Tree;

import java.util.*;

public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return util(nums, 0, nums.length-1);
    }

    public TreeNode util(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }

        int maxPos = maxPoition(nums, start, end);
        TreeNode root = new TreeNode(nums[maxPos]);
        root.left = util(nums, start, maxPos - 1);
        root.right = util(nums, maxPos + 1, end);
        return root;
    }

    public int maxPoition(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxPos = start;
        for(int i = start; i <= end; i++) {
            if(max < nums[i]) {
                max = nums[i];
                maxPos = i;
            }
        }
        return maxPos;
    }

    public static void main(String args[]) throws Exception {
        int arr[] = {3,2,1,6,0,5};
        MaximumBinaryTree sol = new MaximumBinaryTree();
        TreeNode root = sol.constructMaximumBinaryTree(arr);
        System.out.println(root.val);
        System.out.println(root.left.val + "  " + root.right.val);
    }
}
