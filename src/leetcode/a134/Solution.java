package leetcode.a134;

import java.util.Arrays;
import java.util.List;

public class Solution {
    class SegmentTreeForRangeMinimum {
        class Segment {
            int begin, end;
            int min;
            public Segment(int b, int e, int c) {
                begin = b;
                end = e;
                min = c;
            }
        }
        Segment[] segments;
        public SegmentTreeForRangeMinimum(int capacity) {
            segments = new Segment[capacity << 2];
            init(0, 0, capacity);
        }
        private void init(int root, int begin, int end) {
            segments[root] = new Segment(begin, end, Integer.MAX_VALUE);
            if(begin + 1 < end) {
                int mid = (begin + end) / 2;
                init(root * 2 + 1, begin, mid);
                init(root * 2 + 2, mid, end);
            }
        }
        public int getMinimum(int begin, int end) {
            return getMinimum(0, begin, end);
        }
        private int getMinimum(int root, int begin, int end) {
            if(begin <= segments[root].begin && end >= segments[root].end) {
                return segments[root].min;
            }
            if(begin >= segments[root].end || end <= segments[root].begin) {
                return Integer.MAX_VALUE;
            }
            return Math.min(getMinimum(root * 2 + 1, begin, end), getMinimum(root * 2 + 2, begin, end));
        }
        public void update(int index, int val) {
            update(0, index, val);
        }
        private void update(int root, int index, int val) {
            if(segments[root].begin <= index && segments[root].end > index) {
                if(segments[root].begin + 1 != segments[root].end) {
                    update(root * 2 + 1, index, val);
                    update(root * 2 + 2, index, val);
                    segments[root].min = Math.min(segments[root * 2 + 1].min, segments[root * 2 + 2].min);
                }
                else {
                    segments[root].min = val;
                }
            }
        }
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        SegmentTreeForRangeMinimum tree = new SegmentTreeForRangeMinimum(gas.length * 2);
        int acc = 0;
        tree.update(0, 0);
        for(int i = 0; i < gas.length * 2 - 1; ++ i) {
            acc = acc + gas[i % gas.length] - cost[i % gas.length];
            tree.update(i+1, acc);
        }
        for(int i = 0; i < gas.length; ++ i) {
            if(tree.getMinimum(i+1, i+1+gas.length) >= tree.getMinimum(i, i+1)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<int[]> gas = Arrays.asList(
                new int[] {2, 4},
                new int[] {2, 3, 1},
                new int[] {4, 3, 7}
        );
        List<int[]> cost = Arrays.asList(
                new int[] {3, 4},
                new int[] {3, 1, 2},
                new int[] {5, 4, 5}
        );

        Solution s = new Solution();
        for(int i = 0; i < gas.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.canCompleteCircuit(gas.get(i), cost.get(i)));
        }
    }
}