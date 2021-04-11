import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BeautifulArrangement {
    Set<Integer> nums;
    List<Integer> list = new ArrayList<>();
    int N;

    public int countArrangement(int N) {
        this.N = N;
        nums = new HashSet<>();
        for(int i=1; i<=N; i++) {
            nums.add(i);
        }

        return recur(0);
    }

    public int recur(int idx) {
        if(idx == N) {
            //System.out.println("~~~~");
            return 1;
        }

        int sol = 0;
        for(int i=1;i<=(idx+1)/2;i++) {
            if((idx+1) % i == 0) {
                if(nums.remove(i)) {
                    //System.out.print(i + " ");
                    sol += recur(idx+1);
                    nums.add(i);
                }
            }
        }

        int index = idx+1;
        while(index <= N) {
            if(index%(idx+1) == 0) {
                if(nums.remove(index)) {
                    //System.out.print(index + " ");
                    sol += recur(idx+1);
                    nums.add(index);
                }
            }
            index+=(idx+1);
        }
        return sol;
    }

    public static void main(String[] args) {
        BeautifulArrangement obj = new BeautifulArrangement();
        System.out.println(obj.countArrangement(6));
    }
}