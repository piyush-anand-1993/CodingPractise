import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode<T> {
    boolean isLeaf;
    Map<Character, TrieNode> child = new HashMap<>();
    String key;
    List<T> data;

    public TrieNode() {
        this.isLeaf = false;
        this.data = new ArrayList<>();
        this.key = null;
    }

    public TrieNode(T data, boolean isLeaf) {
        this.data = new ArrayList<>();
        if(data != null) this.data.add(data);
        this.isLeaf = isLeaf;
        this.key = null;
    }
}
