package leetcode.a274;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int hIndex(int[] citations) {
        int[] count = new int[citations.length + 1];
        for(int citation : citations) {
            ++ count[Math.min(citation, citations.length)];
        }
        int sum = 0;
        for(int h = citations.length; h > 0; -- h) {
            sum += count[h];
            if(sum >= h) {
                return h;
            }
        }
        return 0;
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