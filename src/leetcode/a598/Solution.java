package leetcode.a598;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int mina = m, minb = n;
        for(int[] op : ops) {
            if(op[0] < mina) {
                mina = op[0];
            }
            if(op[1] < minb) {
                minb = op[1];
            }
        }
        return mina * minb;
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