import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {


        Map<String, TreeMap<Integer, String>> map = new HashMap<>();
        TreeMap<Integer, String> tm = map.getOrDefault("abc", new TreeMap<Integer, String>());

        return null;
    }

    public static void main(String[] args) {

    }

    public int[] findRedundantConnection(int[][] edges) {

        int N = edges.length;
        int[] sol = new int[2];

        DSU dsu = new DSU(N);
        int tmp = edges[0][1];
        System.out.println(tmp);

        for(int i=0; i<N; i++) {
            //int x = edges[i][0];

            int x = edges[i][1];
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
