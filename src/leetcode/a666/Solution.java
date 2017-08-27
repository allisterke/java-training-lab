package leetcode.a666;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int total = 0;
    public int pathSum(int[] nums) {
        int[] tree = new int[31];
        boolean[] exist = new boolean[31];
        for(int n : nums) {
            int level = n / 100;
            int pos = n % 100 / 10;
            int value = n % 10;
            int index = (1 << (level - 1)) - 1 + pos - 1;
            tree[index] = value;
            exist[index] = true;
        }
        if(!exist[0]) {
            return 0;
        }
        total = 0;
        dfs(tree, exist, 0, 0);
        return total;
    }

    void dfs(int[] tree, boolean[] exist, int root, int sum) {
        int left = root * 2 + 1;
        int right = root * 2 + 2;
        sum += tree[root];
        if(!exist[left] && !exist[right]) {
            total += sum;
        }
        else {
            if(exist[left]) {
                dfs(tree, exist, left, sum);
            }
            if(exist[right]) {
                dfs(tree, exist, right, sum);
            }
        }
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {113, 215, 221},
                new int[] {113, 221}
        );
        List results = Arrays.asList(
                12,
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.pathSum(tests.get(i)));
        }
    }
}