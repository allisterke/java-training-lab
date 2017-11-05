package leetcode.a723;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] candyCrush(int[][] board) {
        int R = board.length;
        int C = board[0].length;
        while(true) {
            boolean[][] cleared = new boolean[R][C];
            boolean modified = false;
            for(int i = 0; i < R; ++ i) {
                for(int j = 0, k = 0; j < C; j = k) {
                    k = j+1;
                    if(board[i][j] == 0) {
                        continue;
                    }
                    for(; k < C && board[i][k] == board[i][j]; ++ k);
                    if(k - j >= 3) {
                        modified = true;
                        while (j < k) {
                            cleared[i][j ++] = true;
                        }
                    }
                }
            }
            for(int j = 0; j < C; ++ j) {
                for(int i = 0, k = 0; i < R; i = k) {
                    k = i+1;
                    if(board[i][j] == 0) {
                        continue;
                    }
                    for(; k < R && board[k][j] == board[i][j]; ++ k);
                    if(k - i >= 3) {
                        modified = true;
                        while (i < k) {
                            cleared[i ++][j] = true;
                        }
                    }
                }
            }
            if(!modified) {
                break;
            }
            for(int j = 0; j < C; ++ j) {
                int p = R-1;
                for(int i = R-1; i >= 0; -- i) {
                    if(!cleared[i][j]) {
                        board[p --][j] = board[i][j];
                    }
                }
                while(p >= 0) {
                    board[p --][j] = 0;
                }
            }
        }
        return board;
    }

    public int[][] candyCrush0(int[][] board) {
        int R = board.length;
        int C = board[0].length;
        while(true) {
            boolean[][] visited = new boolean[R][C];
            boolean[][] cleared = new boolean[R][C];
            boolean modified = false;
            for(int i = 0; i < R; ++ i) {
                for(int j = 0; j < C; ++ j) {
                    if(board[i][j] == 0 || visited[i][j]) {
                        continue;
                    }
                    int count = dfs(board, visited, board[i][j], i, j, R, C);
                    if(count >= 3) {
                        modified = true;
                        clear(board, cleared, board[i][j], i, j, R, C);
                    }
                }
            }
            if(!modified) {
                break;
            }
            for(int j = 0; j < C; ++ j) {
                int p = R-1;
                for(int i = R-1; i >= 0; -- i) {
                    if(!cleared[i][j]) {
                        board[p --][j] = board[i][j];
                    }
                }
                while(p >= 0) {
                    board[p --][j] = 0;
                }
            }
        }
        return board;
    }

    void clear(int[][] board, boolean[][] cleared, int type, int i, int j, int R, int C) {
        if(i < 0 || j < 0 || i >= R || j >= C || board[i][j] != type) {
            return;
        }
        cleared[i][j] = true;
        dfs(board, cleared, type, i+1, j, R, C);
        dfs(board, cleared, type, i-1, j, R, C);
        dfs(board, cleared, type, i, j+1, R, C);
        dfs(board, cleared, type, i, j-1, R, C);
    }
    int dfs(int[][] board, boolean[][] visited, int type, int i, int j, int R, int C) {
        if(i < 0 || j < 0 || i >= R || j >= C || visited[i][j] || board[i][j] != type) {
            return 0;
        }
        visited[i][j] = true;
        return  1 +
                dfs(board, visited, type, i+1, j, R, C) +
                dfs(board, visited, type, i-1, j, R, C) +
                dfs(board, visited, type, i, j+1, R, C) +
                dfs(board, visited, type, i, j-1, R, C);
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {{110,5,112,113,114},{210,211,5,213,214},{310,311,3,313,314},{410,411,412,5,414},{5,1,512,3,3},{610,4,1,613,614},{710,1,2,713,714},{810,1,2,1,1},{1,1,2,2,2},{4,1,4,4,1014}}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            int[][] board = s.candyCrush(tests.get(i));
            for(int x = 0; x < board.length; ++ x) {
                for(int y = 0; y < board[x].length; ++ y) {
                    System.out.print(board[x][y]);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }
}