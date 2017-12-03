package leetcode.a740;

import scala.Int;

import java.util.*;

public class Solution {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        List<Integer> n = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        Map<Integer, Integer> cache = new HashMap<>();
        int total = 0;
        for(int i = 0; i < nums.length; ++ i) {
            if(n.isEmpty() || nums[i] > n.get(n.size() - 1) + 1) {
                total += earn(n, c, cache, 0, n.size() - 1);
                n.clear();
                c.clear();
                cache.clear();
                n.add(nums[i]);
                c.add(1);
            }
            else {
                if(nums[i] == n.get(n.size()-1)) {
                    c.set(c.size() - 1, c.get(c.size() - 1) + 1);
                }
                else {
                    n.add(nums[i]);
                    c.add(1);
                }
            }
        }
        total += earn(n, c, cache, 0, n.size() - 1);
        return total;
    }

    int earn(List<Integer> n, List<Integer> c, Map<Integer, Integer> cache, int begin, int end) {
        if(begin > end) {
            return 0;
        }
        int key = (begin << 16) | end;
        if(!cache.containsKey(key)) {
            int total = 0;
            for (int i = begin; i <= end; ++i) {
                total = Math.max(total,
                        earn(n, c, cache, begin, i - 2) + n.get(i) * c.get(i) + earn(n, c, cache, i + 2, end));
            }
            cache.put(key, total);
        }
        return cache.get(key);
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {3, 4, 2},
                        {2, 2, 3, 3, 3, 4}
                }
        );
        List<Integer> results = Arrays.asList(
                6,
                9
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.deleteAndEarn(tests.get(i)));
        }
    }
}