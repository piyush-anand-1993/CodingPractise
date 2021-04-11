import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/all-possible-full-binary-trees/
 */
public class AllPossibleFullBinaryTree {
    static List[] cache = new List[100];

    public List<TreeNode> allPossibleFBT(int N) {
        return util(N);
    }

    public List<TreeNode> util(int N) {
        if(cache[N] != null) {
            return cache[N];
        }
        List<TreeNode> sol = new ArrayList<>();
        if(N == 0)
            return sol;
        if(N == 1) {
            sol.add(new TreeNode(0));
            return sol;
        }
        for(int i=2; i<N; i++) {
            int l = i - 1;
            int r = N - i;

            if(l % 2 == 0 || r % 2 == 0)
                continue;
            List<TreeNode> leftTrees = util(l);
            List<TreeNode> rightTrees  = util(r);

            for(TreeNode left: leftTrees) {
                for(TreeNode right: rightTrees) {
                    TreeNode node = new TreeNode(0);
                    node.left = left;
                    node.right = right;
                    sol.add(node);
                }
            }
        }
        cache[N] = sol;
        return sol;
    }

    public static void main(String[] args) {
        AllPossibleFullBinaryTree obj = new AllPossibleFullBinaryTree();
        List<TreeNode> sol = obj.allPossibleFBT(7);
        System.out.println(sol.size());
    }
}
