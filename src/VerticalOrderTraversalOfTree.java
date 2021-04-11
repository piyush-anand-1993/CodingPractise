import java.util.*;

/*
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        if(root == null) return sol;

        Map<Integer, List<HorizontalOrder>> verticalOrder = new TreeMap<>();
        util(root, 0, 0, verticalOrder);

        for (List<HorizontalOrder> horizontalOrders: verticalOrder.values()) {
            Collections.sort(horizontalOrders);
            List<Integer> horOrder = new ArrayList<>();
            for(HorizontalOrder order: horizontalOrders) {
                horOrder.add(order.val);
            }
            sol.add(horOrder);
        }
        return sol;
    }

    public void util(TreeNode root, int x, int y, Map<Integer, List<HorizontalOrder>> verticalOrder) {
        if(root == null) return;
        util(root.left, x-1, y+1, verticalOrder);

        if(!verticalOrder.containsKey(x)) {
            verticalOrder.put(x, new ArrayList<>());
        }
        List<HorizontalOrder> horizontalOrders = verticalOrder.get(x);
        horizontalOrders.add(new HorizontalOrder(y, root.val));
        verticalOrder.put(x, horizontalOrders);

        util(root.right, x+1,y+1, verticalOrder);
    }

    class HorizontalOrder implements Comparable<HorizontalOrder> {
        int level;
        int val;
        public HorizontalOrder(int level, int val) {
            this.level = level;
            this.val = val;
        }

        @Override
        public int compareTo(HorizontalOrder o) {
            if(o == null)
                return -1;
            if(level - o.level == 0) {
                return val - o.val;
            }
            return level - o.level;
        }
    }
}
