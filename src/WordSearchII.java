import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {

    int row = 0;
    int col = 0;
    boolean visited[][] = new boolean[0][0];

    public List<String> findWords(char[][] board, String[] words) {

        List<String> sol = new ArrayList<>();
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];

        Node root = new Node((char)0);

        //index starting from 1 and not 0 for trie as 0 is to indicate on word ending with that character
        int index = 1;
        for(String word: words) {
            insert(root, word, index++);
        }

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                int idx = dfs(board, i, j, root);
                while(idx > 0) {
                    sol.add(words[idx-1]);
                    idx = dfs(board, i, j, root);
                }
            }
        }

        return sol;
    }

    private int dfs(char[][] board, int i, int j, Node root) {
        //System.out.println(root.c + "\t" + i + "\t" + j);

        if(root.end > 0) {
            return root.end;
        }
        Node curr = root.map.get(board[i][j]);
        if(curr!= null && curr.end > 0) {
            int idx = curr.end;
            curr.end = 0;// setting it back to 0 as already found don't find next time
            return idx;
        }
        if(curr != null) {
            visited[i][j] = true;
            //up
            if(isValid(i+1, j)) {
                //System.out.println("Going to: " + "\t" + (i+1) + "\t" + j);
                //Node next = curr.map.get(board[i + 1][j]);

                int idx= dfs(board, i+1, j, curr);
                if(idx > 0) {
                    visited[i][j] = false;
                    return idx;
                }
            }
            //down
            if(isValid(i-1, j)) {
                //System.out.println("Going to: " + "\t" + (i-1) + "\t" + j);
                //Node next = curr.map.get(board[i-1][j]);

                int idx= dfs(board, i-1, j, curr);
                if(idx > 0) {
                    visited[i][j] = false;
                    return idx;
                }
            }
            //right
            if(isValid(i, j+1)) {
                //System.out.println("Going to: " + "\t" + i + "\t" + (j+1));
                //Node next = curr.map.get(board[i][j+1]);

                int idx= dfs(board, i, j+1, curr);
                if(idx > 0) {
                    visited[i][j] = false;
                    return idx;
                }
            }
            //left
            if(isValid(i, j-1)) {
                //System.out.println("Going to: " + "\t" + i + "\t" + (j-1));
                //Node next = curr.map.get(board[i][j-1]);

                int idx= dfs(board, i, j-1, curr);
                if(idx > 0) {
                    visited[i][j] = false;
                    return idx;
                }
            }
            visited[i][j] = false;
        }
        return -1;
    }

    private boolean isValid(int i, int j) {
        if(i<0 || i >= row || j<0 || j >= col) {
            return false;
        }
        return !visited[i][j];
    }

    private class Node {
        char c;
        int end;
        Map<Character, Node> map;

        Node(char c) {
            this.c = c;
        }
    }

    //keep the index 1 based as 0 will mean no word ending at that character
    private void insert(Node root, String word, int index) {
        Node curr = root;
        for (char c: word.toCharArray()) {
            curr.map.putIfAbsent(c, new Node(c));
            curr = curr.map.get(c);
        }
        curr.end = index;
    }

}
