package leetcode.a096;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numTrees(int n) {
        if(n < 0) {
            return 0;
        }
        int[] trees = new int[n+1];
        trees[0] = 1;
        for(int i = 1; i <= n; ++ i) {
            for(int j = 1; j <= i; ++ j) {
                trees[i] += trees[j-1] * trees[i-j];
            }
        }
        return trees[n];
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