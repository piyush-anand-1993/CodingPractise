/*
https://leetcode.com/problems/print-binary-tree/
 */

import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        int height = height(root);

        List<List<String>> tree = new ArrayList<>(height);
        int col = (int) (Math.pow(2, height)) - 1;

        for(int i=0; i < height; i++) {
            List<String> list = new ArrayList<>(col);
            for(int j=0; j<col; j++) {
                list.add(new String(""));
            }
            tree.add(list);
        }
        util(root, tree, 0, col-1, 0);
        return tree;
    }

    public void util(TreeNode root, List<List<String>> tree, int start, int end, int level) {
        if(root == null)
            return;
        int mid = (start + end) / 2;
        tree.get(level).set(mid, String.valueOf(root.val));
        util(root.left, tree, start, mid - 1, level + 1);
        util(root.right, tree, mid + 1, end, level + 1);
    }

    public int height(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
