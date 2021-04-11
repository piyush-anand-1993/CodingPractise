/*
    https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/#:~:text=Random%20Point%20in%20Non%2Doverlapping%20Rectangles%20%2D%20LeetCode&text=Given%20a%20list%20of%20non,space%20covered%20by%20the%20rectangles.&text=pick%20return%20a%20point%20as,integer%20coordinates%20%5Bp_x%2C%20p_y%5D
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPointsInNonOverlappingRectangles {

    List<Point> points;
    int totalNoOfPoints;
    int eligiblePoints;

    public RandomPointsInNonOverlappingRectangles(int[][] rects) {
        points = new ArrayList<>();
        totalNoOfPoints = 0;

        for(int i = 0; i < rects.length; i++) {
            //picking x axis
            for(int m = rects[i][0]; m <= rects[i][2]; m++) {
                //pick y axis
                for(int n = rects[i][1]; n <= rects[i][3]; n++) {
                    points.add(new Point(m, n));
                    totalNoOfPoints++;
                }
            }
        }
        eligiblePoints = totalNoOfPoints;
    }

    public int[] pick() {
        if(eligiblePoints <= 0) {
            eligiblePoints = totalNoOfPoints;
        }
        Random random = new Random();
        int index = random.nextInt(eligiblePoints);
        Point currPoint = points.get(index);
        int sol[] = {currPoint.x, currPoint.y};
        Collections.swap(points, index, eligiblePoints - 1);
        eligiblePoints--;
        return sol;
    }
}

class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
