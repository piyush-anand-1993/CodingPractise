import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
 *
 *
 *              1
 *            /   \
 *           2     3
 *         /  \   /  \
 *        4   5  6   7
 *
 *                    1
 *                  /   \
 *                1'      3
 *              /  \     /   \
 *             2        3'    7
 *           /  \      /  \    / \
 *         2'     5    6     7'
 *        / \    / \  / \
 *      4       5'     6'
 *    /
 *   4'
 */

public class CloneBinaryTreeRandPointer {

    public TreeNode cloneTree(TreeNode root) {
        insertCloneNodes(root);
        adjustRandomPointer(root);
        return segregateTree(root);
    }

    public void insertCloneNodes(TreeNode root) {
        if(root == null)
            return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        TreeNode temp = new TreeNode(root.data);
        temp.left = root.left;
        root.left = temp;

        insertCloneNodes(left);
        insertCloneNodes(right);
    }

    public void adjustRandomPointer(TreeNode root) {
        if(root == null)
            return;

        TreeNode copyNode = root.left;
        TreeNode left = copyNode != null ? copyNode.left : null;
        TreeNode right = root.right;

        if(root.rand != null) {
            copyNode.rand = root.rand.left;
        }

        adjustRandomPointer(left);
        adjustRandomPointer(right);
    }

    public TreeNode segregateTree(TreeNode root) {
        if(root == null)
            return null;

        TreeNode copyNode = root.left;
        TreeNode left = copyNode != null ? copyNode.left : null;
        TreeNode right = root.right;

        root.left = left;

        if(left != null) {
            copyNode.left = left.left;
        }

        if(root.right != null) {
            copyNode.right = root.right.left;
        }

        segregateTree(left);
        segregateTree(right);

        return copyNode;
    }

    static class TreeNode {
        TreeNode right;
        TreeNode left;
        TreeNode rand;
        int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        CloneBinaryTreeRandPointer obj = new CloneBinaryTreeRandPointer();

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = node2;
        root.right = node3;

        root.rand = node2;
        node2.rand = node3;
        node3.rand = root;

        TreeNode clone = obj.cloneTree(root);
        System.out.println(clone.data);
    }
}
