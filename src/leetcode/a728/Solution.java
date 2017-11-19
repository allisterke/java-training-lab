package leetcode.a728;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> nums = new ArrayList<>();
        for(int n = left; n <= right; ++ n) {
            if(selfDividable(n)) {
                nums.add(n);
            }
        }
        return nums;
    }

    boolean selfDividable(int n) {
        int m = n;
        while (m > 0) {
            int r = m % 10;
            if(r == 0 || n % r != 0) {
                return false;
            }
            m /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(1, 22)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.selfDividingNumbers(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}