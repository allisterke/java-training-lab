package leetcode.a594;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> counts = new TreeMap<>();
        for(int i = 0; i < nums.length; ) {
            int j = i+1;
            for(; j < nums.length && nums[j] == nums[i]; ++ j) ;
            counts.put(nums[i], j-i);
            i = j;
        }
        int lhs = 0;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            Integer next = counts.get(entry.getKey() + 1);
            if(next != null) {
                lhs = Math.max(lhs, entry.getValue() + next);
            }
        }
        return lhs;
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