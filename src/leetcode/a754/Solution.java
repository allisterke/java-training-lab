package leetcode.a754;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int sum = 0;
        for (int i = 1; i <= 100000; ++ i) {
            sum += i;
            if(sum == target) {
                return i;
            }
            if(sum > target) {
                if((sum - target) % 2 == 0) {
                    return i;
                }
                return (i + 1) % 2 == 1 ? i + 1 : i + 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                3,
                2,
                10240000
        );
        List<Integer> results = Arrays.asList(
                2,
                3
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.reachNumber(tests.get(i)));
        }
    }
}