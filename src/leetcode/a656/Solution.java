package leetcode.a656;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        if(A[A.length - 1] < 0) {
            return Collections.emptyList();
        }
        if(A.length == 1) {
            return Arrays.asList(1);
        }
        int[] next = new int[A.length];
        Arrays.fill(next, -1);
        int[] cost = new int[A.length];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[A.length - 1] = A[A.length - 1];
        for(int i = A.length - 1; i >= 0; -- i) {
            if(A[i] == -1 || i != A.length -1 && next[i] == -1) {
                continue;
            }
            for(int j = 1; j <= B && i - j >= 0; ++ j) {
                int previous = i - j;
                if(A[previous] < 0) {
                    continue;
                }
                if(cost[i] + A[previous] <= cost[previous]) {
                    cost[previous] = cost[i] + A[previous];
                    next[previous] = i;
                }
            }
        }
        if(next[0] == -1) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i != -1; i = next[i]) {
            result.add(i + 1);
        }
        return result;
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