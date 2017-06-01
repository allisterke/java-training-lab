package leetcode.a228;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        StringBuilder range = new StringBuilder();
        for(int i = 0; i < nums.length; ) {
            range.setLength(0);
            int j = i+1;
            for(; j < nums.length && nums[j] - nums[j-1] == 1; ++ j)
                ;
            range.append(nums[i]);
            if(j > i+1) {
                range.append("->").append(nums[j-1]);
            }
            summary.add(range.toString());
            i = j;
        }
        return summary;
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