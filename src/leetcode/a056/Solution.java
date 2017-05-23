package leetcode.a056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    public List<Interval> merge(List<Interval> intervals) {
        Interval[] intervalArray = intervals.toArray(new Interval[intervals.size()]);
        Arrays.sort(intervalArray, (a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end));
        List<Interval> merged = new LinkedList<>();
        for(int i = 0; i < intervalArray.length; ) {
            int start = intervalArray[i].start;
            int end = intervalArray[i].end;
            int j = i+1;
            for(; j < intervalArray.length && intervalArray[j].start <= end; ++ j) {
                if(intervalArray[j].end > end) {
                    end = intervalArray[j].end;
                }
            }
            merged.add(new Interval(start, end));
            i = j;
        }
        return merged;
    }

    public static void main(String[] args) {
        List<List<Interval>> tests = Arrays.asList(
                Arrays.asList(
                    new Interval(1, 3),
                    new Interval(2, 6),
                    new Interval(8, 10),
                    new Interval(15, 18)
                )
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.merge(tests.get(i)));
        }
    }
}