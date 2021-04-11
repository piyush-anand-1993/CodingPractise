import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class TrieStr {

    public static TrieStrNode insert(TrieStrNode root, String s) {
        return _insert(root, s, 0);
    }

    public static TrieStrNode _insert(TrieStrNode root, String s, int index) {
        if(root == null) {
            TrieStrNode newRoot = new TrieStrNode();
            newRoot = _insert(newRoot, s, index + 1);
            return newRoot;
        }

        //no character left to insert
        if(index == s.length()) {
            root.value++;
            return root;
        }
        char c = s.charAt(index);

        TrieStrNode next = root.map.get(c);
        if(next != null) {
            _insert(next, s, index+1);
        }
        else {
            TrieStrNode newNode = new TrieStrNode();
            newNode = _insert(newNode , s, index+1);
            root.map.put(c, newNode);
        }
        return root;
    }

}

class TrieStrNode {
    public Map<Character, TrieStrNode> map;
    int value = 0;
    public TrieStrNode() {
        map = new HashMap<>();
        int value = 0;
    }
}
