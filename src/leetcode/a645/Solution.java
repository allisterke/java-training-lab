package leetcode.a645;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = 0, miss = 0;
        for(int i = 0; i < nums.length; ++ i) {
            while (nums[i] != i+1) {
                int next = nums[i];
                if(nums[next - 1] == next) {
                    dup = next;
                    break;
                }
                nums[i] = nums[next - 1];
                nums[next - 1] = next;
            }
        }
        for(int i = 0; i < nums.length; ++ i) {
            if(nums[i] != i+1) {
                miss = i+1;
                break;
            }
        }
        return new int[] { dup, miss };
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