import java.util.*;

/**
 * https://leetcode.com/problems/concatenated-words/
 */

public class ConcatenatedWords {

    Set<Integer> sol;
    Node root;
    String[] words;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        //sol = new LinkedList<>();
        sol = new HashSet<>();
        root = new Node('0');
        this.words = words;

        int index = 0;
        for(String word: words) {
            insert(word, ++index);
        }

        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(1);
        ll.removeFirst();
        //dfs(root, root, new HashSet<>());
        dfs(root, root, new HashMap<>());

        for(int i=0;i < words.length; i++) {

        }
        List<String> finSol = new LinkedList<>();
        for(int i: sol) {
            finSol.add(words[i]);
        }
        return finSol;
    }

/*    private void dfs(Node root1, Node root2, Map<Integer, Integer> wordsUsed) {
        if(root1 == null || root2 == null)
            return;

        if(root1.end > 0 && root2.end > 0) {
            wordsUsed.merge(root2.end - 1, 1, Integer::sum);
            if(wordsUsed.size() >= 2)   sol.add(root1.end-1);
            dfs(root1, root, wordsUsed);
            int count = wordsUsed.get(root2.end-1);

            if(count == 1) {
                wordsUsed.remove(root2.end-1);
            }
            else {
                wordsUsed.put(root2.end-1, count-1);
            }
        }

        for(Map.Entry<Character, Node> entry: root1.map.entrySet()) {
            char next = entry.getKey();
            Node next2 = root2.map.get(next);
            dfs(entry.getValue(), next2, wordsUsed);
        }
    }*/

    private void dfs(Node root1, Node root2, Map<Integer, Integer> wordsUsed) {
        if(root1 == null || root2 == null)
            return;

        if(root2.end > 0) {
            //if(root1.end > 0) {
                wordsUsed.merge(root2.end - 1, 1, Integer::sum);
                if(root1.end > 0 && wordsUsed.size() >= 2)   sol.add(root1.end-1);
                dfs(root1, root, wordsUsed);
                int count = wordsUsed.get(root2.end-1);

                if(count == 1) {
                    wordsUsed.remove(root2.end-1);
                }
                else {
                    wordsUsed.put(root2.end-1, count-1);
                }
            //}
        }

        for(Map.Entry<Character, Node> entry: root1.map.entrySet()) {
            char next = entry.getKey();
            Node next2 = root2.map.get(next);
            dfs(entry.getValue(), next2, wordsUsed);
        }
    }

    private void insert(String s, int index) {
        if(s.length() == 0) return;
        Node curr = root;
        for(char c: s.toCharArray()) {
            curr.map.putIfAbsent(c, new Node(c));
            curr = curr.map.get(c);
        }
        curr.end = index;
    }
    private class Node {
        char c;
        int end;
        Map<Character, Node> map;

        Node(char c) {
            this.c =c;
            end =0;
            map = new HashMap<>();
        }
    }

    public static void main(String args[]) {
        ConcatenatedWords obj = new ConcatenatedWords();

        //String[] words = {"cat", "dog", "catdog", "catra", "catracat", "catcat", "catcatcat"}; //catdog, catracat, catcatcat
        //String[] words = {"cat", "catcat", "dog", "catdog"}; //catdog
        //String[] words = {"cat", "dog", "catdog"}; //catdog
        //String[] words = {"cat", "catcat", "catcatcat"}; //catcatcat
        //String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        //"catsdogcats","dogcatsdog","ratcatdogcat"
        //String[] words = {"cats","dog","catsdogcats"};
        String[] words = {""};
        System.out.println(obj.findAllConcatenatedWordsInADict(words).toString());
    }
}

//"catsdogcats","dogcatsdog","ratcatdogcat"