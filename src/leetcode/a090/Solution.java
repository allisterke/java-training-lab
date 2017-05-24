package leetcode.a090;

import java.util.*;

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        backtrack(nums, 0, subset, result);
        return result;
    }

    void backtrack(int[] nums, int offset, List<Integer> subset, List<List<Integer>> result) {
        if(offset >= nums.length) {
            result.add(new ArrayList<>(subset));
        }
        else {
            int next = offset + 1;
            for(; next < nums.length && nums[next] == nums[offset]; ++ next)
                ;
            backtrack(nums, next, subset, result);
            for(int i = 0; i < next - offset; ++ i) {
                subset.add(nums[offset]);
                backtrack(nums, next, subset, result);
            }
            for(int i = 0; i < next - offset; ++ i) {
                subset.remove(subset.size() - 1);
            }
        }
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