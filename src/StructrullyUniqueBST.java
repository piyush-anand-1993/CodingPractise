public class StructrullyUniqueBST {

    int arr[];
    public int numTrees(int n) {
        if(n==0) return 0;

        arr = new int[n+1];
        arr[0] = 1;

        return util(n);
    }

    private int util(int n) {
        if(arr[n] != 0) return arr[n];
        int count = 0;
        for(int i=0; i < n; i++) {
            count = count + (util(i) * util(n-i-1));
        }
        arr[n] = count;
        return count;
    }

    public static void main(String args[]) throws Exception {
        StructrullyUniqueBST uniqBST = new StructrullyUniqueBST();

        System.out.println(uniqBST.numTrees(2));
        System.out.println(uniqBST.numTrees(3));
    }
}
