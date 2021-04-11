import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/increasing-order-search-tree/
 */
public class IncreasingOrderSearchTree {
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) return null;
        TreeNode[] newRoot = new TreeNode[1];
        TreeNode[] prev = new TreeNode[1];
        util(root, newRoot, prev);
        return newRoot[0];
    }

    public void util(TreeNode root, TreeNode[] newRoot, TreeNode[] prev) {
        if(root == null) return;

        util(root.left, newRoot, prev);

        if(newRoot[0] == null && prev[0] == null) {
            newRoot[0] = root;
        }
        root.left = null;
        if(prev[0] != null) {
            prev[0].right = root;
        }
        prev[0] = root;

        util(root.right, newRoot, prev);
    }
}
