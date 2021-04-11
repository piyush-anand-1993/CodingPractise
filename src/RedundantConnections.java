/*
 *  https://leetcode.com/problems/redundant-connection/submissions/
 * Disjoint Union Set DSU
 */
public class RedundantConnections {

    public int[] findRedundantConnection(int[][] edges) {

        int N = edges.length;
        int[] sol = new int[2];

        DSU dsu = new DSU(N+1);

        for(int i=0; i<N; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            if(!dsu.union(x, y)) {
                sol[0] = x;
                sol[1] = y;
                return sol;
            }
        }
        return sol;
    }

    class DSU {

        int[] parent;
        int size;

        public DSU(int size) {
            this.size = size;
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
        }

        int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // true: x and y are in different union and are now added into same set after this operatiom
        // false: are already in same union
        boolean union(int x, int y) {
            int parX = find(x);
            int parY = find(y);

            if(parX == parY)
                return false;

            parent[find(x)] = find(y);
            return true;
        }
    }

}
