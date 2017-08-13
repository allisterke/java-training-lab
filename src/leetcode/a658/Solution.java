package leetcode.a658;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static class Diff {
        int diff, n;
        public Diff(int diff, int n) {
            this.diff = diff;
            this.n = n;
        }
    }
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        Diff[] diffs = new Diff[arr.size()];
        for(int i = 0; i < arr.size(); ++ i) {
            diffs[i] = new Diff(Math.abs(arr.get(i) - x), arr.get(i));
        }
        Arrays.sort(diffs, (a, b) -> { return a.diff != b.diff ? Integer.compare(a.diff, b.diff) : Integer.compare(a.n, b.n); });
        int[] result = new int[k];
        for(int i = 0; i < k; ++ i) {
            result[i] = diffs[i].n;
        }
        Arrays.sort(result);
        List<Integer> list = new ArrayList<>();
        for(int n : result) {
            list.add(n);
        }
        return list;
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