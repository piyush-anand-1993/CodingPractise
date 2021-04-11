/**
 * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/
 */
public class LongestChunckedPalindromeDecompostion {

    public int longestDecomposition(String text) {
        if(text == null || text.isEmpty())
            return 0;

        int mem[] = new int[(text.length()+1)/2];
        return recur(text, 0, mem);
    }

    public int recur(String text, int idx, int mem[]) {
        if(idx >= mem.length)
            return 0;

        if(mem[idx] > 0)
            return mem[idx];

        //int lastIdx = text.length()-1;
        mem[idx] = 1;
        for(int i=idx; i<text.length()/2; i++) {
            if(areEqual(text, idx, i)) {
                int currLen = 2 + recur(text,i+1,mem);
                if(currLen > mem[idx])
                    mem[idx] = currLen;
            }
        }
        return mem[idx];
    }

    public boolean areEqual(String text, int start, int end) {
        int startLast = text.length() - (end - start + 1) - start;
        while (start <= end) {
            if(text.charAt(start++) != text.charAt(startLast++)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        LongestChunckedPalindromeDecompostion obj = new LongestChunckedPalindromeDecompostion();
        System.out.println(obj.longestDecomposition("volvolvo"));
        System.out.println(obj.longestDecomposition("voabcvo"));
        System.out.println(obj.longestDecomposition("volvol"));
        System.out.println(obj.longestDecomposition("aaaaa"));
        System.out.println(obj.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
        System.out.println(obj.longestDecomposition("antaprezatepzapreanta"));
        System.out.println(obj.longestDecomposition("merchant"));
    }
}
