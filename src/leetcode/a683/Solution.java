package leetcode.a683;

import java.util.Arrays;
import java.util.List;

public class Solution {
    static class SegmentTree {
        class Segment {
            int begin, end;
            int count;
            public Segment(int b, int e, int c) {
                begin = b;
                end = e;
                count = c;
            }
        }
        Segment[] segments;
        public SegmentTree(int capacity) {
            segments = new Segment[capacity << 2];
            init(0, 0, capacity);
        }
        private void init(int root, int begin, int end) {
            segments[root] = new Segment(begin, end, 0);
            if(begin + 1 < end) {
                int mid = (begin + end) / 2;
                init(root * 2 + 1, begin, mid);
                init(root * 2 + 2, mid, end);
            }
        }
        public int count(int begin, int end) {
            return count(0, begin, end);
        }
        private int count(int root, int begin, int end) {
            if(begin <= segments[root].begin && end >= segments[root].end) {
                return segments[root].count;
            }
            if(begin >= segments[root].end || end <= segments[root].begin) {
                return 0;
            }
            return count(root * 2 + 1, begin, end) + count(root * 2 + 2, begin, end);
        }
        public void increment(int index) {
            increment(0, index);
        }
        private void increment(int root, int index) {
            if(segments[root].begin <= index && segments[root].end > index) {
                ++ segments[root].count;
                if(segments[root].begin + 1 != segments[root].end) {
                    increment(root * 2 + 1, index);
                    increment(root * 2 + 2, index);
                }
            }
        }
    }

    public int kEmptySlots(int[] flowers, int k) {
        SegmentTree tree = new SegmentTree(flowers.length);
        for(int i = 0; i < flowers.length; ++ i) {
            int x = flowers[i] - 1;
            tree.increment(x);
            int a1 = x - k - 1;
            if(a1 >= 0 && tree.count(a1, x+1) == 2 && tree.count(a1+1, x+1) == 1) {
                return i+1;
            }
            int a2 = x + k + 2;
            if(a2 <= flowers.length && tree.count(x, a2) == 2 && tree.count(x, a2-1) == 1) {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        System.out.println(s.kEmptySlots(new int[]{6,5,8,9,7,1,10,2,3,4}, 2));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}