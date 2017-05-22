package leetcode.a039;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private void tryCombination(int[] candidates, int target, int sum, int offset, List<Integer> combination, List<List<Integer>> combinations) {
        if(sum == target) {
            combinations.add(new ArrayList<>(combination));
        }
        else {
            for(int i = offset; i < candidates.length && sum + candidates[i] <= target; ++ i) {
                combination.add(candidates[i]);
                tryCombination(candidates, target, sum + candidates[i], i, combination, combinations);
                combination.remove(combination.size() - 1);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> combinations = new ArrayList<>();
        tryCombination(candidates, target, 0, 0, combination, combinations);
        return combinations;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {2, 3, 6, 7}
        );
        List<Integer> targets = Arrays.asList(
                7
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.combinationSum(tests.get(i), targets.get(i)));
        }
    }
}