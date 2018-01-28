package leetcode.a773;

import java.util.*;

public class Solution {
    public int slidingPuzzle(int[][] board) {
        Board start = new Board(board);
        if(start.checkDone()) {
            return 0;
        }
        Map<Board, Integer> visited = new HashMap<>();
        Queue<Board> queue = new ArrayDeque<>();
        queue.add(start);
        visited.put(start, 0);
        while (!queue.isEmpty()) {
            Board head = queue.poll();
            int d = visited.get(head);
            board = head.getBoard();
            int[] zero = findZero(board);
            int x = zero[0], y = zero[1];
            int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            for (int[] direction : directions) {
                int i = x + direction[0];
                int j = y + direction[1];
                if(i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    int tmp = board[i][j];
                    board[i][j] = board[x][y];
                    board[x][y] = tmp;

                    Board next = new Board(board);
                    if(next.checkDone()) {
                        return d + 1;
                    }
                    if(!visited.containsKey(next)) {
                        visited.put(next, d + 1);
                        queue.add(next);
                    }

                    tmp = board[i][j];
                    board[i][j] = board[x][y];
                    board[x][y] = tmp;
                }
            }
        }
        return -1;
    }

    int[] findZero(int[][] board) {
        for (int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board[i].length; ++ j) {
                if(board[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    static class Board {
        int[][] board = new int[2][3];
        int hash = 0;

        public Board(int[][] board) {
            for (int i = 0; i < this.board.length; ++ i) {
                for (int j = 0; j < this.board[i].length; ++ j) {
                    this.board[i][j] = board[i][j];
                }
            }
            for (int i = 0; i < this.board.length; ++ i) {
                hash = hash * 31 + Arrays.hashCode(this.board[i]);
            }
        }

        int[][] getBoard() {
            int[][] board = new int[this.board.length][this.board[0].length];

            for (int i = 0; i < this.board.length; ++ i) {
                for (int j = 0; j < this.board[i].length; ++ j) {
                    board[i][j] = this.board[i][j];
                }
            }
            return board;
        }

        public boolean checkDone() {
            int k = 1;
            for (int i = 0; i < board.length; ++ i) {
                for (int j = 0; j < board[i].length; ++ j) {
                    if (i == board.length - 1 && j == board[i].length - 1) {
                        if (board[i][j] != 0) {
                            return false;
                        }
                    }
                    else {
                        if(board[i][j] != k ++) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Board) {
                Board o = (Board)obj;
                for (int i = 0; i < board.length; ++ i) {
                    for (int j = 0; j < board[i].length; ++ j) {
                        if(board[i][j] != o.board[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {{1,2,3},{4,0,5}},
                        {{1,2,3},{5,4,0}},
                        {{4,1,2},{5,0,3}},
                        {{3,2,4},{1,5,0}}
                }
        );
        List<Integer> results = Arrays.asList(
                1, -1, 5, 14
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.slidingPuzzle(tests.get(i)));
        }
    }
}