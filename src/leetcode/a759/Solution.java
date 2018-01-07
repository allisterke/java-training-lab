package leetcode.a759;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

public class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<List<Integer>> mainPoints = new ArrayList<>();
        for (List<Interval> avail : avails) {
            for(Interval interval : avail) {
                mainPoints.add(Arrays.asList(interval.start, 0));
                mainPoints.add(Arrays.asList(interval.end, 1));
            }
        }
        mainPoints.sort(Comparator.<List<Integer>>comparingInt(list -> list.get(0)).thenComparingInt(list -> list.get(1)));
        int last = -1;
        int used = 0;
        List<Interval> free = new ArrayList<>();
        for (List<Integer> point: mainPoints) {
            if(point.get(1) == 0) {
                ++ used;
            }
            else {
                -- used;
            }
            if(used == 1) {
                if(last > 0) {
                    free.add(new Interval(last, point.get(0)));
                }
                last = -1;
            }
            else if(used == 0) {
                last = point.get(0);
            }
        }
        return free;
    }

    public static void main(String[] args) {
        List<List<List<Interval>>> tests = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(new Interval(1, 2), new Interval(5, 6)),
                        Arrays.asList(new Interval(1, 3)),
                        Arrays.asList(new Interval(4, 10))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
                        Arrays.asList(new Interval(2, 4)),
                        Arrays.asList(new Interval(2, 5), new Interval(9, 12))
                )
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.employeeFreeTime(tests.get(i)));
        }
    }
}