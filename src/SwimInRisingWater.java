import java.util.ArrayDeque;
import java.util.Queue;

/*
https://leetcode.com/problems/swim-in-rising-water/
 */
public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int maxTime = -1;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] > maxTime)
                    maxTime = grid[i][j];
            }
        }
        int low = grid[0][0];
        int high = maxTime;

        while(low <= high){
            int mid = (low + high)/2;
            int[][] visited = new int[grid.length][grid[0].length];
            visited[0][0] = 1;
            if(dfs(grid, visited, 0, 0, mid)) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return high + 1;
    }

    public boolean dfs(int[][] grid, int[][] visited, int r, int c, int currTime) {
        if((r == grid.length - 1) && (c == grid[0].length - 1))
            return true;
        //up
        //boolean up = false;
        if(isAvailable(grid, visited, r-1, c, currTime)) {
            visited[r-1][c] = 1;
            //up = dfs(grid, visited, r-1, c, currTime);
            if(dfs(grid, visited, r-1, c, currTime)) return true;
            //visited[r-1][c] = 0;
        }
        //if(up) return up;

        //right
        //boolean right = false;
        if(isAvailable(grid, visited, r, c+1, currTime)) {
            visited[r][c+1] = 1;
            //right = dfs(grid, visited, r, c+1, currTime);
            if(dfs(grid, visited, r, c+1, currTime)) return true;
            //visited[r][c+1] = 0;
        }
        //if(right) return right;

        //down
        //boolean down = false;
        if(isAvailable(grid, visited, r+1, c, currTime)) {
            visited[r+1][c] = 1;
            //down = dfs(grid, visited, r+1, c, currTime);
            if(dfs(grid, visited, r+1, c, currTime)) return true;
            //visited[r+1][c] = 0;
        }
        //if(down) return down;

        //left
        //boolean left = false;
        if(isAvailable(grid, visited, r, c-1, currTime)) {
            visited[r][c-1] = 1;
            //left = dfs(grid, visited, r, c-1, currTime);
            if(dfs(grid, visited, r, c-1, currTime)) return true;
            //visited[r][c-1] = 0;
        }
        //if(left) return left;

        return false;
    }

    public boolean isAvailable(int[][] grid, int[][] visited, int r, int c, int currTime) {
        //is a valid tile
        if(r >= grid.length || c >= grid[0].length || r < 0 || c < 0)
            return false;
        //is already visited
        if(visited[r][c] != 0)
            return false;
        //if the elevation is greater than currTime, we cannot swim through it
        if(grid[r][c] > currTime)
            return false;
        return true;
    }

    public static void main(String[] args) {
        //int grid[][] = {{0,2},{1,3}};
        int grid[][] = {{26,99,80,1,89,86,54,90,47,87},{9,59,61,49,14,55,77,3,83,79},{42,22,15,5,95,38,74,12,92,71},{58,40,64,62,24,85,30,6,96,52},{10,70,57,19,44,27,98,16,25,65},{13,0,76,32,29,45,28,69,53,41},{18,8,21,67,46,36,56,50,51,72},{39,78,48,63,68,91,34,4,11,31},{97,23,60,17,66,37,43,33,84,35},{75,88,82,20,7,73,2,94,93,81}};
        int visited[][] = new int[grid.length][grid[0].length];
        visited[0][0] = 1;
        SwimInRisingWater obj = new SwimInRisingWater();
        //boolean sol = obj.dfs(grid, visited, 0, 0, 70);
        int sol = obj.swimInWater(grid);
        System.out.println(sol);
    }
}
