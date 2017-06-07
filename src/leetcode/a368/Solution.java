package leetcode.a368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) {
            return Arrays.asList();
        }
        Arrays.sort(nums);
        int[] previous = new int[nums.length];
        int[] count = new int[nums.length];
        int opt = 0;
        for(int i = 0; i < nums.length; ++ i) {
            previous[i] = i;
            count[i] = 0;
            for(int j = 0; j < i; ++ j) {
                if(nums[i] % nums[j] == 0 && count[j] > count[i]) {
                    count[i] = count[j];
                    previous[i] = j;
                }
            }
            ++ count[i];
            if(count[i] > count[opt]) {
                opt = i;
            }
        }
        List<Integer> subset = new ArrayList<>();
        subset.add(nums[opt]);
        while (previous[opt] != opt) {
            opt = previous[opt];
            subset.add(nums[opt]);
        }
        return subset;
    }
    public List<Integer> largestDivisibleSubset0(int[] nums) {
        if(nums.length == 0) {
            return Arrays.asList();
        }
        Arrays.sort(nums);
        List<Integer>[] dp = new List[nums.length];
        List<Integer> opt = Collections.emptyList();
        for(int i = 0; i < nums.length; ++ i) {
            dp[i] = Collections.emptyList();
            int index = i;
            for(int j = 0; j < i; ++ j) {
                if(nums[i] % nums[j] == 0 && dp[j].size() > dp[index].size()) {
                    index = j;
                }
            }
            dp[i] = new ArrayList<>(dp[index]);
            dp[i].add(nums[i]);
            if(dp[i].size() > opt.size()) {
                opt = dp[i];
            }
        }
        return opt;
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