package leetcode.a289;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; ++ i) {
            for(int j = 0; j < board[i].length; ++ j) {
                int liveNeighbors = 0;
                for(int p = -1; p <= 1; ++ p) {
                    for(int q = -1; q <= 1; ++ q) {
                        int x = i + p;
                        int y = j + q;
                        if(x >= 0 && y >= 0 && x < board.length && y < board[i].length) {
                            if(p == -1 || p == 0 && q == -1) {
                                liveNeighbors += board[x][y] >> 8;
                            }
                            else {
                                liveNeighbors += board[x][y];
                            }
                        }
                    }
                }
                board[i][j] <<= 8;
                if(board[i][j] == (1 << 8)) {
                    -- liveNeighbors;
                    if(liveNeighbors >= 2 && liveNeighbors <= 3) {
                        board[i][j] |= 1;
                    }
                }
                else {
                    if(liveNeighbors == 3) {
                        board[i][j] |= 1;
                    }
                }
            }
        }
        for(int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] &= 0xf;
            }
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