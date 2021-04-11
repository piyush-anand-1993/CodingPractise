/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 */

public class LongestWordInDictonary {
    public String longestWord(String[] words) {
        if(words == null || words.length == 0)
            return "";

        TrieStrNode root = new TrieStrNode();
        root.value =1;
        String sol = "";
        for(String word: words) {
            root = TrieStr.insert(root, word);
        }

        for(String word: words) {
            boolean isValid = traverse(root, word, 0);
            if(isValid && word.length() >= sol.length()) {
                if (word.length() != sol.length() || word.compareTo(sol) <= 0) {
                    sol = word;
                }
            }
        }
        return sol;
    }

    public static boolean traverse(TrieStrNode root, String word, int index) {
        if(index == word.length()) {
            return true;
        }

        if(root.value == 0) {
            return false;
        }

        TrieStrNode next = root.map.get(word.charAt(index));
        return traverse(next, word, index+1);
    }

    public static void main(String[] args) {
        LongestWordInDictonary obj = new LongestWordInDictonary();
        //String[] words = {"w","wo","wor","worl", "world"};
        //System.out.println(obj.longestWord(words));

        String[] words2 = {"a", "b", "c"};
        System.out.println(obj.longestWord(words2));
    }

}
