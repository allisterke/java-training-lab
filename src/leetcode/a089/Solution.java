package leetcode.a089;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> codes = new ArrayList<>();
        codes.add(0);
        for(int i = 1; i <= n; ++ i) {
            for(int j = codes.size() - 1; j >= 0; -- j) {
                codes.add(codes.get(j) | (1 << (i-1)));
            }
        }
        return codes;
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