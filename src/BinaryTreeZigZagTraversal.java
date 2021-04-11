/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */

import java.util.*;

public class BinaryTreeZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrderv1(TreeNode root) {
    //this does not work
        List<List<Integer>> sol = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        Stack<TreeNode> s = new Stack<>();

        if(root != null)q.add(root);
        while(!q.isEmpty() || !s.isEmpty() ) {
            List<Integer> list1 = new ArrayList<>();
            while(!q.isEmpty()) {
                TreeNode top = q.poll();
                list1.add(top.val);
                if(top.left != null) s.add(top.left);
                if(top.right != null) s.add(top.right);
            }
            if(!list1.isEmpty()) sol.add(list1);

            List<Integer> list2 = new ArrayList<>();
            while(!s.isEmpty()) {
                TreeNode top = s.pop();
                list2.add(top.val);
                if(top.left != null) q.add(top.left);
                if(top.right != null) q.add(top.right);
            }
            if(!list2.isEmpty()) sol.add(list2);
        }
        return sol;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        //Queue<TreeNode> q = new ArrayDeque<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        if(root != null)s1.add(root);
        while(!s1.isEmpty() || !s2.isEmpty() ) {
            List<Integer> list1 = new ArrayList<>();
            while(!s1.isEmpty()) {
                TreeNode top = s1.pop();
                list1.add(top.val);
                if(top.left != null) s2.add(top.left);
                if(top.right != null) s2.add(top.right);
            }
            if(!list1.isEmpty()) sol.add(list1);

            List<Integer> list2 = new ArrayList<>();
            while(!s2.isEmpty()) {
                TreeNode top = s2.pop();
                list2.add(top.val);
                if(top.right != null) s1.add(top.right);
                if(top.left != null) s1.add(top.left);
            }
            if(!list2.isEmpty()) sol.add(list2);
        }
        return sol;
    }
}
