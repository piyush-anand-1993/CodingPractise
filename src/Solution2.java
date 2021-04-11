import java.util.*;

public class Solution2 {

    private int time = 0;

    List<Integer> criticalRouters(int numRouters, int numLinks,
                                  ArrayList<ArrayList<Integer>> links)
    {
        /*
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numRouters; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for(ArrayList<Integer> link: links) {
            adj.get(link.get(0)-1).add(link.get(1) -1);
            adj.get(link.get(1)-1).add(link.get(0) -1);
        }
        for(int i=0; i<numRouters; i++) {
            Collections.sort(adj.get(i));
        }

         */

        boolean visited[] = new boolean[numRouters];
        int disc[] = new int[numRouters];
        int low[] = new int[numRouters];
        int parent[] = new int[numRouters];
        boolean ap[] = new boolean[numRouters]; // To store articulation points

        for (int i = 0; i < numRouters; i++)
        {
            parent[i] = -1;
            visited[i] = false;
            ap[i] = false;
        }

        for (int i = 0; i < numRouters; i++) {
            if (!visited[i])
                APUtil(i, visited, disc, low, parent, ap, links);
        }

        List<Integer> sol = new ArrayList<>();
        for (int i = 0; i < numRouters; i++) {
            if (ap[i])
                sol.add(i);
        }
        return sol;
    }

    void APUtil(int u, boolean visited[], int disc[],
                int low[], int parent[], boolean ap[], ArrayList<ArrayList<Integer>> adj)
    {

        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                APUtil(v, visited, disc, low, parent, ap, adj);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1)
                    ap[u] = true;

                if (parent[u] != -1 && low[v] >= disc[u])
                    ap[u] = true;
            } else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }
    }

    public static void main(String args[]) {
        Solution2 solution2 = new Solution2();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());

        //adj.get(0).add()
    }
}
