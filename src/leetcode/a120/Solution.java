package leetcode.a120;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.isEmpty()) {
            return 0;
        }
        int[] min1 = new int[triangle.size()];
        int[] min2 = new int[triangle.size()];
        min1[0] = triangle.get(0).get(0);
        for(int i = 1; i < triangle.size(); ++ i) {
            min2[0] = min1[0] + triangle.get(i).get(0);
            for(int j = 1; j < i; ++ j) {
                min2[j] = Math.min(min1[j-1], min1[j]) + triangle.get(i).get(j);
            }
            min2[i] = min1[i-1] + triangle.get(i).get(i);
            for (int j = 0; j < triangle.get(i).size(); ++ j) {
                min1[j] = min2[j];
            }
        }
        return Arrays.stream(min1).min().getAsInt();
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