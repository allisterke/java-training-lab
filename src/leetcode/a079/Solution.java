package leetcode.a079;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean exist(char[][] board, String word) {
        int R = board.length;
        int C = R > 0 ? board[0].length : 0;
        if(R == 0 || C == 0) {
            return word.isEmpty();
        }
        boolean[][] used = new boolean[R][];
        for(int i = 0; i < R; ++ i) {
            used[i] = new boolean[C];
        }
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(checkExistence(board, used, i, j, R, C, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkExistence(char[][] board, boolean[][] used, int i, int j, int R, int C, String word, int offset) {
        if(offset >= word.length()) {
            return true;
        }
        if(i < 0 || j < 0 || i >= R || j >= C || used[i][j] || word.charAt(offset ++) != board[i][j]) {
            return false;
        }
        used[i][j] = true;
        try {
            return checkExistence(board, used, i+1, j, R, C, word, offset) ||
                    checkExistence(board, used, i-1, j, R, C, word, offset) ||
                    checkExistence(board, used, i, j+1, R, C, word, offset) ||
                    checkExistence(board, used, i, j-1, R, C, word, offset);
        } finally {
            used[i][j] = false;
        }
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