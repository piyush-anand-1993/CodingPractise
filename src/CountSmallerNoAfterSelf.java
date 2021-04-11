import java.util.*;

/*
* https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountSmallerNoAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sol = new LinkedList<>();
        if(nums == null || nums.length == 0)
            return sol;

        TreeNode1 root = new TreeNode1(nums[nums.length - 1]);
        sol.add(0, 0);
        for(int i = nums.length -2; i>=0; i--) {
            int[] nodesLessThan = new int[1];
            root = insert(root, nums[i], nodesLessThan);
            sol.add(0, nodesLessThan[0]);
        }
        return sol;
    }

    public static TreeNode1 insert(TreeNode1 root, int value, int[] nodesLessThan) {
        if(root == null) {
            return new TreeNode1(value);
        }
        if(value == root.value) {
            root.count++;
            nodesLessThan[0] = nodesLessThan[0] + root.nodesOnLeftHand;
        }
        else if(value < root.value) {
            root.nodesOnLeftHand++;
            root.left = insert(root.left, value, nodesLessThan);
        }
        else if(value > root.value) {
            nodesLessThan[0] = nodesLessThan[0] + root.nodesOnLeftHand + root.count;
            root.right = insert(root.right, value, nodesLessThan);
        }
        return root;
    }
}


class TreeNode1 {
    public TreeNode1 left;
    public TreeNode1 right;
    public int value;
    public int count;
    public int nodesOnLeftHand;

    public TreeNode1(int value) {
        this.value = value;
        count = 1;
        nodesOnLeftHand = 0;
    }
}
