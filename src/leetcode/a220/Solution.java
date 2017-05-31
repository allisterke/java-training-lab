package leetcode.a220;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    static class IndexedLong implements Comparable<IndexedLong> {
        int index;
        long val;

        public IndexedLong(long v, int i) {
            val = v;
            index = i;
        }

        @Override
        public int compareTo(IndexedLong o) {
            return val != o.val
                    ? Long.compare(val, o.val)
                    : Integer.compare(index, o.index);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof IndexedLong
                    && ((IndexedLong)(obj)).val == val
                    && ((IndexedLong)(obj)).index == index;
        }
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int tt) {
        long t = tt;
        if(k <= 0 || t < 0) {
            return false;
        }
        TreeSet<IndexedLong> set = new TreeSet();
        for (int i = 0; i < nums.length; ++ i) {
            IndexedLong lower = set.ceiling(new IndexedLong(nums[i] - t, 0));
            IndexedLong upper = set.floor(new IndexedLong(nums[i] + t, Integer.MAX_VALUE));
            if(lower != null && upper != null && lower.val <= upper.val) {
                return true;
            }
            set.add(new IndexedLong(nums[i], i));
            if(i >= k) {
                set.remove(new IndexedLong(nums[i-k], i-k));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {0,10,22,15,0,5,22,12,1,5}
        );
        List<Integer> ks = Arrays.asList(
                3
        );
        List<Integer> ts = Arrays.asList(
                3
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.containsNearbyAlmostDuplicate(tests.get(i), ks.get(i), ts.get(i)));
        }
    }
}