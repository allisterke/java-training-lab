package leetcode.a040;

import java.util.*;

public class Solution {
    private void tryCombination2(int[] candidates, int target, int sum, int offset, List<Integer> combination, Set<List<Integer>> combinations) {
        if(sum == target) {
            combinations.add(new ArrayList<>(combination));
        }
        else {
            for(int i = offset; i < candidates.length && sum + candidates[i] <= target; ++ i) {
                combination.add(candidates[i]);
                tryCombination2(candidates, target, sum + candidates[i], i + 1, combination, combinations);
                combination.remove(combination.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        Set<List<Integer>> combinations = new HashSet<>();
        tryCombination2(candidates, target, 0, 0, combination, combinations);
        return new ArrayList<>(combinations);
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
            System.out.println(s.combinationSum2(tests.get(i), targets.get(i)));
        }
    }
}