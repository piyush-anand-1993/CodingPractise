import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.codechef.com/problems/ONEKING
 */

public class OneKing {

    public int noOfBombs(List<Kingdom> kingdoms) {

        kingdoms.sort(Comparator.comparingInt(o -> o.l));

        //System.out.println(kingdoms.toString());

        int sol = 0;
        int currIdx = 0;


        while(currIdx < kingdoms.size()) {
            sol++;
            Kingdom currK = kingdoms.get(currIdx);
            int next = currIdx + 1;
            int rightLimit = currK.r;
            while(next < kingdoms.size()) {
                Kingdom nextK = kingdoms.get(next);
                if(rightLimit < nextK.l) {
                    break;
                }
                rightLimit = Math.min(rightLimit, nextK.r);
                next++;
            }
            currIdx = next;
        }
        return sol;
    }

    public static void main(String args[]) {

        OneKing obj = new OneKing();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        List<Kingdom> list = new ArrayList<>();
        while(t > 0) {
            int n = s.nextInt();
            while(n > 0) {
                int a = s.nextInt();
                int b = s.nextInt();
                list.add(new Kingdom(a, b));
                n--;
            }
            System.out.println(obj.noOfBombs(list));
            t--;
            list.clear();
        }
    }
}

class Kingdom {
    int l;
    int r;

    public Kingdom(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public String toString() {
        return "[" + l + " " + r + "]";
    }
}