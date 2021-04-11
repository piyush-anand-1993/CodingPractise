import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/alien-dictionary/1
 */

public class AlienDictionary {

    public String findOrder(String [] dict, int N, int K)
    {
        Node[] arr = new Node[K];
        Map<Character, Node> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for(int i=0; i<K; i++) {
            arr[i] = new Node((char)('a' + i));
            map.put((char)('a' + i), arr[i]);
            set.add((char)('a' + i));
        }

        for(int i=1; i<N; i++) {

            int l = Math.min(dict[i-1].length(), dict[i].length());
            int idx = 0;

            while(idx < l && dict[i-1].charAt(idx) == dict[i].charAt(idx))
                idx++;

            if(idx == l)
                continue;

            Node src = map.get(dict[i-1].charAt(idx));

            src.next = map.get(dict[i].charAt(idx));
            set.remove(dict[i].charAt(idx));
        }

        Node head = null;

        for(char c: set) {
            head = map.get(c);
        }

        StringBuilder str = new StringBuilder();

        while(head != null) {
            str.append(head.c);
            head = head.next;
        }

        return str.toString();
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();

        String[] dict = {"baa","abcd","abca","cab","cad"};
        System.out.println(ad.findOrder(dict, 5, 4));


        String[] dict2 = {"caa","aaa","aab"};
        System.out.println(ad.findOrder(dict2, 3, 3));
    }
}

class Node {
    char c;
    Node next;

    public Node(char c) {
        this.c = c;
    }
}
