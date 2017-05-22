package leetcode.a036;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;


public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][][] used = new boolean[3][][];
        for(int i = 0; i < 3; ++ i) {
            used[i] = new boolean[9][];
            for(int j = 0; j < 9; ++ j) {
                used[i][j] = new boolean[9];
            }
        }
        IntBinaryOperator[] ts = new IntBinaryOperator[] {
                (i, j) -> i,
                (i, j) -> j,
                (i, j) -> i/3*3 + j/3
        };
        for(int i = 0; i < 9; ++ i) {
            for(int j = 0; j < 9; ++ j) {
                if(board[i][j] == '.') {
                    continue;
                }
                for(int k = 0; k < 3; ++ k) {
                    int x = ts[k].applyAsInt(i, j);
                    int y = board[i][j] - '1';
                    if(used[k][x][y]) {
                        return false;
                    }
                    used[k][x][y] = true;
                }
            }
        }
        return true;
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