package leetcode.a693;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public boolean hasAlternatingBits(int n) {
        int last = n & 1;
        for(n >>= 1; n != 0; n >>= 1) {
            int current = n & 1;
            if(current == last) {
                return false;
            }
            last = current;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                5,
                7,
                9
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.hasAlternatingBits(tests.get(i)));
        }
    }
}