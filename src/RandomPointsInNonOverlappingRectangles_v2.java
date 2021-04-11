/*
    https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/#:~:text=Random%20Point%20in%20Non%2Doverlapping%20Rectangles%20%2D%20LeetCode&text=Given%20a%20list%20of%20non,space%20covered%20by%20the%20rectangles.&text=pick%20return%20a%20point%20as,integer%20coordinates%20%5Bp_x%2C%20p_y%5D
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPointsInNonOverlappingRectangles_v2 {

    int rects[][];
    int i,j,k;

    public RandomPointsInNonOverlappingRectangles_v2(int[][] rects) {
        this.rects = rects;
        i=0;
        j=0;
        k=0;
    }

    /*
     *  loop through all rectangles, select a point and return, increase the counter
     *  i -> index of the rectangle array, increment when all the points in the current rectangle are exhausted
     *  j -> x axis of the rectangle, increment each time when next point is required
     *  k -> y axix of the rectangle, increment each time j has used up the entire x axis
     */
    public int[] pick() {
        if(rects[i][0] + j <= rects[i][2]) {
            int sol[] = {(rects[i][0] + j++), rects[i][1] + k};
            return sol;
        }
        else {
            j=0;
            k++;
            if(rects[i][1] + k <= rects[i][3]) {
                int sol[] = {(rects[i][0] + j++), rects[i][1] + k};
                return sol;
            }
            else {
                i++;
                k=0;
                if(i == rects.length)
                    i=0;
            }
        }
        return pick();
    }
}

