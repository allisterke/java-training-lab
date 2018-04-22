package leetcode.a822;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < fronts.length; ++ i) {
            if (fronts[i] == backs[i]) {
                continue;
            }
            int[] candidates = {fronts[i], backs[i]};
            for (int c : candidates) {
                boolean exist = false;
                for (int j = 0; j < fronts.length; ++j) {
                    if (fronts[j] == c && backs[j] == c) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    min = Math.min(min, c);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        return min;
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