/*
https://leetcode.com/problems/course-schedule/
 */

import java.util.*;

public class CourseSchedule {

    Map<Integer, List<Integer>> reverse;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        reverse = new HashMap<>();
        for(int i = 0; i<prerequisites.length; i++) {
            if(!reverse.containsKey(prerequisites[i][1])) {
                reverse.put(prerequisites[i][1], new ArrayList<>());
            }
            reverse.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        return topologicalSort(numCourses);
    }

    public boolean topologicalSort(int n) {
        Stack<Integer> s = new Stack<>();
        int visited[] = new int[n];
        int color = 1; // color should never be 0 as it signifies not visited
        for(int i=0; i<n; i++) {
            if(visited[i] == 0) {
                if(!topoUtil(i, visited, s, color)) {
                    return false;
                }
            }
            color++;
        }
        return true;
    }

    public boolean topoUtil(int v, int[] visited, Stack<Integer> s, int color) {
        visited[v] = color * -1;
        if(reverse.get(v) == null) {
            visited[v] = color;
            return true;
        }
        for(int vNext: reverse.get(v)) {
            if(visited[vNext] == (color * -1)) {
                return false;
            }
            if(visited[vNext] == 0) {
                if(!topoUtil(vNext, visited, s, color))
                    return false;
            }
        }
        visited[v] = color;
        return true;
    }

    public static void main(String args[]) {
        CourseSchedule obj = new CourseSchedule();
        int arr[][] = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(obj.canFinish(4, arr));
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] a = grid[0];
        int len = grid[0].length;
        return 0;
    }
}
