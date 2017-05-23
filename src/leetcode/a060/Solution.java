package leetcode.a060;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    private int countPermutations(int n) {
        if(n <= 0) {
            return 1;
        }
        return n * countPermutations(n-1);
    }

    private int getPermutation(Set<Integer> choices, int permutation, int k) {
        if(choices.isEmpty()) {
            return permutation;
        }
        Integer[] choiceArray = choices.toArray(new Integer[choices.size()]);
        int count = countPermutations(choices.size() - 1);
        int index = k / count;
        permutation = permutation * 10 + choiceArray[index];
        choices.remove(choiceArray[index]);
        return getPermutation(choices, permutation, k - index * count);
    }

    public String getPermutation(int n, int k) {
        Set<Integer> choices = new TreeSet<>(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList()));
        return String.valueOf(getPermutation(choices, 0, k-1));
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(5, 1)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.getPermutation(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}