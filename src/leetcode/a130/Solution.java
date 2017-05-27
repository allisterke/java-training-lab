package leetcode.a130;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Solution {
    public void solve(char[][] board) {
        int R = board.length;
        int C = R > 0 ? board[0].length : 0;
        markEdge(board, R, C);
        flipAndRecover(board, R, C);
    }

    void markEdge(char[][] board, int R, int C) {
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if((i == 0 || j == 0 || i == R-1 || j == C-1) && board[i][j] == 'O') {
//                    markEdgeIteratively(board, R, C, i, j);
                    markEdgeRecursively(board, R, C, i, j);
                }
            }
        }
    }

    static class Pair {
        int first, second;
        public Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

    static Pair[] directions = new Pair[] {
            new Pair(0, 1),
            new Pair(0, -1),
            new Pair(1, 0),
            new Pair(-1, 0)
    };

    void markEdgeIteratively(char[][] board, int R, int C, int i, int j) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(i, j));
        board[i][j] = 'E';
        while (!queue.isEmpty()) {
            Pair head = queue.poll();
            for(Pair d : directions) {
                int x = head.first + d.first;
                int y = head.second + d.second;
                if(x >= 0 && y >= 0 && x < R && y < C && board[x][y] == 'O') {
                    board[x][y] = 'E';
                    queue.add(new Pair(x, y));
                }
            }
        }
    }

    void markEdgeRecursively(char[][] board, int R, int C, int i, int j) {
        if(i < 0 || j < 0 || i >= R || j >= C || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'E';
        markEdgeRecursively(board, R, C, i+1, j);
        markEdgeRecursively(board, R, C, i-1, j);
        markEdgeRecursively(board, R, C, i, j+1);
        markEdgeRecursively(board, R, C, i, j-1);
    }

    void flipAndRecover(char[][] board, int R, int C) {
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if(board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
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