import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII_v2 {

    int row = 0;
    int col = 0;
    boolean visited[][] = new boolean[0][0];

    public List<String> findWords(char[][] board, String[] words) {
        List<String> sol = new ArrayList<>();

        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];

        Map<Character, List<Pair>> posMap = new HashMap<>();
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                List<Pair> list = posMap.computeIfAbsent(board[i][j], k -> new ArrayList<>());
                list.add(new Pair(i, j));
            }
        }

        boolean flag = false;
        for(String word: words) {
            flag = false;

            char[] sArr = word.toCharArray();
            List<Pair> list = posMap.get(word.charAt(0));
            if(list != null) {
                for(Pair point: list) {
                    flag = dfs(board, point.x, point.y, sArr, 0);
                    if(flag) {
                        sol.add(word);
                        break;
                    }
                }
            }
        }

        return sol;
    }

    public boolean dfs(char[][] board, int i, int j, char[] sArr, int index) {

        //System.out.println(s + "\t" + i + "\t" + j);

        if(board[i][j] != sArr[index]) {
            return false;
        }

        if(index == sArr.length-1) {
            return true;
        }

        visited[i][j] = true;
        //up
        boolean up = isValid(board, i+1, j) && dfs(board, i+1, j, sArr, index + 1);
        if(up) {
            visited[i][j] = false;
            return true;
        }
        //down
        boolean down = isValid(board, i-1, j) && dfs(board, i-1, j, sArr, index + 1);
        if(down) {
            visited[i][j] = false;
            return true;
        }
        //right
        boolean right = isValid(board, i, j+1) && dfs(board, i, j+1, sArr, index + 1);
        if(right) {
            visited[i][j] = false;
            return true;
        }
        //left
        boolean left = isValid(board, i, j-1) && dfs(board, i, j-1, sArr, index + 1);
        if(left) {
            visited[i][j] = false;
            return true;
        }

        visited[i][j] = false;
        return false;
    }

    public boolean isValid(char[][] board, int i, int j) {
        if(i<0 || i >= row || j<0 || j >= col) {
            return false;
        }
        return !visited[i][j];
    }

    private class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

}
