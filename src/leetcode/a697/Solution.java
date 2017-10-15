package leetcode.a697;

import java.util.*;

public class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> beginMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        for(int i = 0; i < nums.length; ++ i) {
            int n = nums[i];
            countMap.putIfAbsent(n, 0);
            countMap.put(n, countMap.get(n) + 1);
            beginMap.putIfAbsent(n, i);
            endMap.putIfAbsent(nums[nums.length - 1 - i], nums.length - i);
        }
        List<Integer> mostSet = new ArrayList<>();
        int most = 0;
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() == most) {
                mostSet.add(entry.getKey());
            }
            else if (entry.getValue() > most) {
                most = entry.getValue();
                mostSet.clear();
                mostSet.add(entry.getKey());
            }
        }
        int shortest = Integer.MAX_VALUE;
        for(int n : mostSet) {
            shortest = Math.min(shortest, endMap.get(n) - beginMap.get(n));
        }
        return shortest;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        new int[] {1, 2, 2, 3, 1},
                        new int[] {1, 2, 2, 3, 1, 4, 2}
                }
        );
        List<Integer> results = Arrays.asList(
                2,
                6
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findShortestSubArray(tests.get(i)));
        }
    }
}