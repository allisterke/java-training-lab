package leetcode.a624;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxDistance(int[][] arrays) {
        int min = arrays[0][0], max = arrays[0][arrays[0].length-1];
        int md = 0;
        for(int i = 1; i < arrays.length; ++ i) {
            md = Math.max(md, Math.max(Math.abs(arrays[i][0] - max), Math.abs(arrays[i][arrays[i].length - 1] - min)));
            min = Math.min(min, arrays[i][0]);
            max = Math.max(max, arrays[i][arrays[i].length - 1]);
        }
        return md;
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