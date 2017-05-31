package leetcode.a216;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        for(int i = 0; i < (1 << 9); ++ i) {
            if(Integer.bitCount(i) != k) {
                continue;
            }
            int sum = 0;
            for(int j = 0; j < 9; ++ j) {
                if((i & (1 << j)) != 0) {
                    sum += j + 1;
                }
            }
            if(sum != n) {
                continue;
            }
            List<Integer> combination = new ArrayList<>();
            for(int j = 0; j < 9; ++ j) {
                if((i & (1 << j)) != 0) {
                    combination.add(j + 1);
                }
            }
            combinations.add(combination);
        }
        return combinations;
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