package leetcode.a821;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Solution {

    public int[] shortestToChar(String S, char C) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < S.length(); ++ i) {
            if (S.charAt(i) == C) {
                treeSet.add(i);
            }
        }
        int[] ds = new int[S.length()];
        Arrays.fill(ds, Integer.MAX_VALUE);
        for (int i = 0; i < S.length(); ++ i) {
            Integer floor = treeSet.floor(i);
            if (floor != null) {
                ds[i] = Math.min(ds[i], i - floor);
            }
            Integer ceil = treeSet.ceiling(i);
            if (ceil != null) {
                ds[i] = Math.min(ds[i], ceil - i);
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}