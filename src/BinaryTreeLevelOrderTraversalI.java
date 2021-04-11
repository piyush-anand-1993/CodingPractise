import java.util.*;

public class BinaryTreeLevelOrderTraversalI {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> sol = new LinkedList<>();
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();

        if(root != null) q1.add(root);
        while(!q1.isEmpty() || !q2.isEmpty()) {
            List<Integer> list1 = new ArrayList<>();
            while(!q1.isEmpty()) {
                TreeNode top = q1.poll();
                list1.add(top.val);
                if(top.left != null) q2.add(top.left);
                if(top.right != null) q2.add(top.right);
            }
            if(!list1.isEmpty()) sol.addFirst(list1);

            List<Integer> list2 = new ArrayList<>();
            while(!q2.isEmpty()) {
                TreeNode top = q2.poll();
                list2.add(top.val);
                if(top.left != null) q1.add(top.left);
                if(top.right != null) q1.add(top.right);
            }
            if(!list2.isEmpty()) sol.addFirst(list2);
        }
        return sol;
    }
}
