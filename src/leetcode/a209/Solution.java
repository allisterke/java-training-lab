package leetcode.a209;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        for(int begin = 0, end = 0, sum = 0; begin < nums.length;) {
            if(sum < s) {
                if(end < nums.length) {
                    sum += nums[end];
                    ++ end;
                }
                else {
                    break;
                }
            }
            else {
                minLength = Math.min(minLength, end - begin);
                sum -= nums[begin];
                ++ begin;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {2,3,1,2,4,3}
        );
        List<Integer> ss = Arrays.asList(
                7
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minSubArrayLen(ss.get(i), tests.get(i)));
        }
    }
}