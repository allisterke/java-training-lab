package leetcode.a077;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private void combine(int n, int k, int next, List<Integer> combination, List<List<Integer>> combinations) {
        for (int i = next; n - i + 1 + combination.size() >= k; ++ i) {
            combination.add(i);
            if(combination.size() == k) {
                combinations.add(new ArrayList<>(combination));
            }
            else {
                combine(n, k, i+1, combination, combinations);
            }
            combination.remove(combination.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> combinations = new ArrayList<>();
        if(k > 0) {
            combine(n, k, 1, combination, combinations);
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