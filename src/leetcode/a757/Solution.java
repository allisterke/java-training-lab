package leetcode.a757;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((a) -> a[1]));
        int[] covered = new int[intervals.length];
        List<Integer> points = new ArrayList<>();
        for (int[] interval : intervals) {
            int count = checkPoints(points, interval[0], interval[1]);
            if(count >= 2) {
                continue;
            }
            if(count == 0) {
                points.add(interval[1] - 1);
            }
            points.add(interval[1]);
        }
        return points.size();
    }

    int checkPoints(List<Integer> points, int a, int b) {
        int count = 0;
        for (int point : points) {
            if(point >= a && point <= b) {
                ++ count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.intersectionSizeTwo(tests.get(i)));
        }
    }
}