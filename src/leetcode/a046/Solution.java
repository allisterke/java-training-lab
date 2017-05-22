package leetcode.a046;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private int[] nextPermutation(int[] nums) {
        int k = nums.length - 1;
        for(; k > 0 && nums[k-1] >= nums[k]; -- k);
        if(k <= 0) {
            return null;
        }

        int index = k-1;
        for(k = nums.length - 1; nums[k] <= nums[index]; -- k);
        int tmp = nums[k];
        nums[k] = nums[index];
        nums[index] = tmp;

        for(++ index, k = nums.length - 1; index < k; ++ index, -- k) {
            tmp = nums[k];
            nums[k] = nums[index];
            nums[index] = tmp;
        }

        return nums;
    }
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();
        for(; nums != null; nums = nextPermutation(nums)) {
            permutations.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        return permutations;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1, 2, 3}
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.permute(tests.get(i)));
        }
    }
}