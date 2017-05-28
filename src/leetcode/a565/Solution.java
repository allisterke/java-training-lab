package leetcode.a565;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; ++ i) {
            if(!visited[i]) {
                int count = 0;
                for (int index = i; !visited[index]; index = nums[index]) {
                    visited[index] = true;
                    ++count;
                }
                if (count > max) {
                    max = count;
                }
            }
        }
        return max;
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