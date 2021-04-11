import java.util.*;

public class BoundryTraversalOfBinaryTree {
    public ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> sol = new ArrayList<>();
        ArrayList<Integer> leafs = new ArrayList<>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        printLeft(A, left);
        printLeafs(A, leafs);
        printRight(A.right, right);

        sol.addAll(left);
        //sol.add(-1);
        sol.addAll(leafs);
        //sol.add(-1);
        Collections.reverse(right);
        sol.addAll(right);

        Random r = new Random();
        r.nextInt(4);

        return sol;
    }

    public void printLeft(TreeNode root, ArrayList<Integer> sol) {
        if(root == null) return;
        if(root.left != null) {
            sol.add(root.val);
            printLeft(root.left, sol);
        }
        else if(root.right != null) {
            sol.add(root.val);
            printLeft(root.right,sol);
        }
    }

    public void printRight(TreeNode root, ArrayList<Integer> sol) {
        if(root == null) return;
        if(root.right != null) {
            //System.out.print("@ " + root.val + " @ ");
            sol.add(root.val);
            printRight(root.right, sol);
        }
        else if(root.left != null) {
            //System.out.print("@ " + root.val + " @ ");
            sol.add(root.val);
            printRight(root.left,sol);
        }
    }

    public void printLeafs(TreeNode root, ArrayList<Integer> sol) {
        if(root == null) return;
        printLeafs(root.left, sol);
        if(root.left == null && root.right == null) {
            //System.out.print("@" + root.val +"@ ");
            sol.add(root.val);
        }
        printLeafs(root.right, sol);
    }
}
