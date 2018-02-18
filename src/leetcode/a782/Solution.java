package leetcode.a782;

import java.util.*;

public class Solution {
    static class Board {
        int[][] board;
        int hash;

        public Board(int[][] board) {
            int N = board.length;
            this.board = new int[N][N];
            for (int i = 0; i < N; ++ i) {
                for(int j = 0; j < N; ++ j) {
                    this.board[i][j] = board[i][j];
                }
            }
            hash = calcHash(board);
        }

        int calcHash(int[][] board) {
            int hash = 0;
            for (int i = 0; i < board.length; ++ i) {
                hash = hash * 31 + Arrays.hashCode(board[i]);
            }
            return hash;
        }

        Board swapRow(int r1, int r2) {
            Board board = new Board(this.board);
            for (int i = 0; i < this.board.length; ++ i) {
                int tmp = board.board[r1][i];
                board.board[r1][i] = board.board[r2][i];
                board.board[r2][i] = tmp;
            }
            board.hash = calcHash(board.board);
            return board;
        }

        Board swapColumn(int c1, int c2) {
            Board board = new Board(this.board);
            for (int i = 0; i < this.board.length; ++ i) {
                int tmp = board.board[i][c1];
                board.board[i][c1] = board.board[i][c2];
                board.board[i][c2] = tmp;
            }
            board.hash = calcHash(board.board);
            return board;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof Board) {
                Board b = (Board)o;
                if(b.board.length == board.length) {
                    for(int i = 0; i < board.length; ++ i) {
                        for(int j = 0; j < board.length; ++ j) {
                            if(b.board[i][j] != board[i][j]) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        public boolean chessLike() {
            for (int i = 0; i < board.length; ++ i) {
                for (int j = 0; j < board.length; ++ j) {
                    if(i+1 < board.length && board[i+1][j] == board[i][j]) {
                        return false;
                    }
                    if(j+1 < board.length && board[i][j+1] == board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public int movesToChessboard(int[][] board) {
        int ones = 0, zeros = 0;
        for(int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board.length; ++ j) {
                if(board[i][j] == 0) {
                    ++ zeros;
                }
                else {
                    ++ ones;
                }
            }
        }
        if(Math.abs(ones - zeros) > 1) {
            return -1;
        }
        Board b = new Board(board);
        if(b.chessLike()) {
            return 0;
        }
        Map<Board, Integer> visited = new HashMap<>();
        Queue<Board> queue = new ArrayDeque<>();
        visited.put(b, 0);
        queue.add(b);

        int N = board.length;
        while (!queue.isEmpty()) {
            Board h = queue.poll();
            int d = visited.get(h);
            for (int i = 0; i < N; ++ i) {
                for (int j = i+1; j < N; ++ j) {
                    Board[] next = {h.swapRow(i, j), h.swapColumn(i, j)};
                    for (Board tmp : next) {
                        if (visited.containsKey(tmp)) {
                            continue;
                        }
                        if (tmp.chessLike()) {
                            return d + 1;
                        }
                        visited.put(tmp, d + 1);
                        queue.add(tmp);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {
                                {1, 1, 0, 0},
                                {0, 0, 1, 1},
                                {1, 1, 0, 0},
                                {0, 0, 1, 1}
                        },
                        {
                                {1, 0, 1, 0},
                                {1, 0, 1, 0},
                                {0, 1, 0, 1},
                                {0, 1, 0, 1}
                        },
                        {
                                {0, 1, 1, 0},
                                {0, 1, 1, 0},
                                {1, 0, 0, 1},
                                {1, 0, 0, 1}
                        },
                        {
                                {0, 1},
                                {1, 0}
                        },
                        {
                                {1, 0},
                                {1, 0}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.movesToChessboard(tests.get(i)));
        }
    }
}