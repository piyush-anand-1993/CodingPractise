import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/freedom-trail/
 *
 * Input: ring = "godding", key = "gd"
 * Output: 4
 */
public class FreedomTrail {

    private List<Map<Character, List<Position>>> maps;
    private int[][] dp = new int[0][];

    public int findRotateSteps(String ring, String key) {
        maps = new ArrayList<>();
        dp = new int[ring.length()][key.length()];

        preProcess(ring.toCharArray());
        util(key, 0, maps.get(0), 0);

        return dp[0][0];
    }

    private int util(String key, int index, Map<Character, List<Position>> currMap, int ringPos) {
        if(index == key.length()) {
            return 0;
        }
        if(dp[ringPos][index] > 0) {
            //System.out.println("current steps:\t" + currSteps);
            //System.out.println("DP:\t" + ringPos + "\t" +index + "\t" + dp[ringPos][index]);
            return dp[ringPos][index];
        }

        List<Position> positions = currMap.get(key.charAt(index));
        int minVal = Integer.MAX_VALUE;
        for(Position pos: positions) {
            int stepsFromPos = util(key, index+1, maps.get(pos.index), pos.index);
            int curr = stepsFromPos + pos.steps + 1;
            minVal = Math.min(minVal, curr);
        }

        dp[ringPos][index] = minVal;
        //System.out.println("~DP:\t" + ringPos + "\t" +index + "\t" + dp[ringPos][index]);

        return minVal;
    }

    private void preProcess(char[] ring) {

        for(int i=0; i<ring.length; i++) {
            Map<Character, List<Position>> currMap = new HashMap<>();
            maps.add(currMap);
            int forward = (i + 1) % ring.length;
            int backward = ((i - 1) % ring.length + ring.length) % ring.length;
            int steps = 1;

            List<Position> currList = currMap.computeIfAbsent(ring[i], k -> new ArrayList<>());
            currList.add(new Position(i, 0));

            int moves = 1;
            while(moves < ring.length) {
                List<Position> forList = currMap.computeIfAbsent(ring[forward], k -> new ArrayList<>());
                forList.add(new Position(forward, steps));

                if(forward != backward) {
                    List<Position> backList = currMap.computeIfAbsent(ring[backward], k -> new ArrayList<>());
                    backList.add(new Position(backward, steps));
                }
                forward = (forward+1) % ring.length;
                backward = ((backward-1) % ring.length + ring.length) % ring.length;
                steps++;
                moves+=2;
            }
        }
    }

    public static void main(String[] args) {
        //int i = (-1 % 7 + 7) % 7;
        //System.out.println(i);
        FreedomTrail ft = new FreedomTrail();
        System.out.println(ft.findRotateSteps("godding", "dd"));

        //System.out.println(ft.findRotateSteps("nyngl", "ynll"));
        System.out.println(ft.findRotateSteps("nyngl", "yyynnnnnnlllggg"));
    }

    class Position {
        int index;
        int steps;

        Position(int index, int steps) {
            this.index = index;
            this.steps = steps;
        }
    }
}


//0 1 2 3 4
//1 2 3 4 5
