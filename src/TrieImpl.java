import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieImpl<T> {

    private TrieNode<T> root = new TrieNode<>(null, true);
    private List<TrieNode<T>> leafNodes = new ArrayList<>();

    public void add(String s, T data) {
        addutil(root, s.toLowerCase(), 0, data);
    }

    private void addutil(TrieNode<T> root, String s, int level, T data) {
        if(s == null || s.length() == level) {
            root.isLeaf = true;
            root.data.add(data);
            root.key = s;
            leafNodes.add(root);
            return;
        }
        char c = s.charAt(level);
        if (root.child.get(c) == null) {
            root.child.put(c, new TrieNode());
        }
        addutil(root.child.get(c), s, level + 1, data);
    }

    public Map<String, List<T>> getLeafKeyValues() {
        Map<String, List<T>> map = new HashMap<>();
        for(TrieNode<T> leaf: leafNodes) {
            if(!leaf.isLeaf) {
                new RuntimeException("leaf not a leaf error");
            }
            map.put(leaf.key, leaf.data);
        }
        return map;
    }
}
