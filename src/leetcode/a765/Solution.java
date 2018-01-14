package leetcode.a765;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minSwapsCouples(int[] row) {
        int swaps = 0;
        for (int i = 0; i < row.length; i += 2) {
            int next = row[i] % 2 == 0 ? row[i] + 1 : row[i] - 1;
            if(row[i+1] != next) {
                for (int j = i+2; j < row.length; ++ j) {
                    if(row[j] == next) {
                        row[j] = row[i+1];
                        break;
                    }
                }
                row[i+1] = next;
                ++ swaps;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {0, 2, 1, 3},
                        {3, 2, 0, 1}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minSwapsCouples(tests.get(i)));
        }
    }
}