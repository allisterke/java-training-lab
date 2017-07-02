package leetcode.a633;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean judgeSquareSum(int cc) {
        long c = cc;
        for(long i = 0; i * i <= c; ++ i) {
            long r = c - i * i;
            long root = (long)Math.sqrt(r);
            if(root * root == r) {
                return true;
            }
        }
        return false;
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