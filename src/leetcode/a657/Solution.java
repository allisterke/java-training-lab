package leetcode.a657;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for(char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    ++ y;
                    break;
                case 'D':
                    -- y;
                    break;
                case 'L':
                    -- x;
                    break;
                case 'R':
                    ++ x;
                    break;
            }
        }
        return x == 0 && y == 0;
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