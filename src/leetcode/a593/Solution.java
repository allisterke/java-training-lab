package leetcode.a593;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int[][] reorder(int[][] points) {
        int[] p1 = points[0];
        Arrays.sort(points, (int[] p2, int[] p3) -> Long.compare(distance(p2, p1), distance(p3, p1)));
        int[] tmp = points[0];
        points[0] = points[1];
        points[1] = tmp;
        return points;
    }

    private long distance(int[] p1, int[] p2) {
        return (long)(p1[0] - p2[0])*(p1[0] - p2[0]) + (long)(p1[1] - p2[1])*(p1[1] - p2[1]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = reorder(new int[][] { p1, p2, p3, p4 });
        long d = distance(points[0], points[1]);
        if(d == 0) {
            return false;
        }

        for(int i = 0; i < 4; ++ i) {
            if(distance(points[i], points[(i+1)%4]) != d) {
                return false;
            }
            if((long)(points[(i+1)%4][0] - points[i][0]) * (points[(i+2)%4][0] - points[(i+1)%4][0]) +
                    (long)(points[(i+1)%4][1] - points[i][1]) * (points[(i+2)%4][1] - points[(i+1)%4][1]) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        s.validSquare(new int[]{0, 0}, new int[]{1,1}, new int[]{1,0}, new int[]{0,1});
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}