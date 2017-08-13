package leetcode.a659;

import java.util.*;

public class Solution {

    public boolean isPossible(int[] nums) {
        if(nums.length < 3) {
            return false;
        }
        Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>(); // last, (length, count)
        for(int i = 0; i < nums.length; ++ i) {
            TreeMap<Integer, Integer> treeMap = map.get(nums[i] - 1);
            if(treeMap == null) {
                map.putIfAbsent(nums[i], new TreeMap<>());
                treeMap = map.get(nums[i]);
                treeMap.putIfAbsent(1, 0);
                treeMap.put(1, treeMap.get(1) + 1);
            }
            else {
                int count = treeMap.firstKey();
                if(treeMap.get(count) == 1) {
                    treeMap.remove(count);
                    if(treeMap.isEmpty()) {
                        map.remove(nums[i] - 1);
                    }
                }
                else {
                    treeMap.put(count, treeMap.get(count) - 1);
                }
                ++ count;
                map.putIfAbsent(nums[i], new TreeMap<>());
                treeMap = map.get(nums[i]);
                treeMap.putIfAbsent(count, 0);
                treeMap.put(count, treeMap.get(count) + 1);
            }
        }
        for(Map.Entry<Integer, TreeMap<Integer, Integer>> entry : map.entrySet()) {
            for (Integer n : entry.getValue().keySet()) {
                if(n < 3) {
                    return false;
                }
            }
        }
        return true;
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