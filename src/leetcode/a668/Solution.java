package leetcode.a668;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 0, high = m*n + 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int total = count(m, n, mid);
            if(total >= k) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        return high;
    }

    int count(int m, int n, int p) {
        int total = 0;
        for(int i = 1; i <= m; ++ i) {
            total += Math.min(p / i, n);
        }
        return total;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(2, 3, 6),
                Arrays.asList(3, 3, 5),
                Arrays.asList(1, 3, 2),
                Arrays.asList(45, 12, 471),
                Arrays.asList(38, 40, 955)
        );
        List<Integer> results = Arrays.asList(
                6,
                3,
                2,
                312,
                437
        );

        Solution s = new Solution();

        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findKthNumber(tests.get(i).get(0),
                    tests.get(i).get(1),
                    tests.get(i).get(2)));
        }
    }
}