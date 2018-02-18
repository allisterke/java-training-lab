package leetcode.a780;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if(tx == sx && ty == sy) {
                return true;
            }
            if(tx == sx && (ty - sy) % tx == 0) {
                return true;
            }
            if(ty == sy && (tx - sx) % ty == 0) {
                return true;
            }
            if(tx > ty) {
                tx %= ty;
            }
            else {
                ty %= tx;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
//                Arrays.asList(1, 2, (int)1e9, 2),
//                Arrays.asList(1, 8, 4, 15),
                Arrays.asList(1, 1, 3, 5),
                Arrays.asList(1, 1, 2, 2),
                Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 1, (int)1e9, (int)1e9-1)
        );
        List<Boolean> results = Arrays.asList(
                true,
                false,
                true
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.reachingPoints(tests.get(i).get(0),
                    tests.get(i).get(1),
                    tests.get(i).get(2),
                    tests.get(i).get(3)
                    ));
        }
    }
}