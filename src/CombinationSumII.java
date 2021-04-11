import java.util.*;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {

    int target;
    int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        this.target =target;
        this.candidates = candidates;

        Arrays.sort(candidates);
        Set<List<Integer>> sol = new HashSet<>();

        int temp[] = new int[candidates.length];
        //int dp[][] = new int[candidates.length+1][target+1];
        //dfs(sol, new LinkedList<>(), 0, 0);
        dfs2(sol, temp, 0, 0, 0);

        return new ArrayList<>(sol);
    }

    private void dfs(Set<List<Integer>> sol, LinkedList<Integer> currList, int index, int currSum) {

        if(currSum == target) {
            sol.add(new ArrayList<>(currList));
        }

        if(index >= candidates.length || currSum > target) {
            return;
        }

        //do not take current value
        dfs(sol, currList, index+1, currSum);

        //take the current value
        currList.addFirst(candidates[index]);
        dfs(sol, currList, index+1, currSum+candidates[index]);
        currList.removeFirst();
    }

    private void dfs2(Set<List<Integer>> sol, int[] currList, int idx, int index, int currSum) {

        if(currSum == target) {
            sol.add(toList(currList, idx));
        }

        if(index >= candidates.length || currSum > target) {
            return;
        }

        //do not take current value
        dfs2(sol, currList,idx, index+1, currSum);

        //take the current value
        currList[idx] = candidates[index];
        dfs2(sol, currList,idx+1, index+1, currSum+candidates[index]);
    }

    private List<Integer> toList(int[] arr, int index) {
        List<Integer> list = new ArrayList<>(index+1);

        for(int i=0;i<index;i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
