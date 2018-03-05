package leetcode.a794;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean validTicTacToe(String[] board) {
        int x = 0, o = 0;
        for (int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board[i].length(); ++ j) {
                if(board[i].charAt(j) == 'O') {
                    ++ o;
                }
                else if(board[i].charAt(j) == 'X'){
                    ++ x;
                }
            }
        }
        if(x != o && x != o+1) {
            return false;
        }
        if(!checkGameOver(board)) {
            return true;
        }
        for (int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board[i].length(); ++ j) {
                if(board[i].charAt(j) == ' ') {
                    continue;
                }
                if(board[i].charAt(j) == 'X' && x != o+1) {
                    continue;
                }
                if(board[i].charAt(j) == 'O' && x != o) {
                    continue;
                }
                String[] tmp = board.clone();
                StringBuilder builder = new StringBuilder(board[i]);
                builder.setCharAt(j, ' ');
                tmp[i] = builder.toString();
                if(!checkGameOver(tmp)) {
                    return true;
                }
            }
        }
        return false;
    }

    char[][] getCheckList(String[] board) {
        char[][] checkList = new char[8][];
        for (int i = 0; i < 3; ++ i) {
            checkList[i] = board[i].toCharArray();
        }
        for (int i = 0; i < 3; ++ i) {
            checkList[i+3] = new char[] {board[0].charAt(i), board[1].charAt(i), board[2].charAt(i)};
        }
        checkList[6] = new char[] {board[0].charAt(0), board[1].charAt(1), board[2].charAt(2)};
        checkList[7] = new char[] {board[0].charAt(2), board[1].charAt(1), board[2].charAt(0)};
        return checkList;
    }

    boolean checkGameOver(String[] board) {
        for (char[] checkList : getCheckList(board)) {
            if (checkGameOver(checkList)) {
                return true;
            }
        }
        return false;
    }

    boolean checkGameOver(char[] cs) {
        if (cs[0] == ' ') {
            return false;
        }
        return cs[0] == cs[1] && cs[1] == cs[2];
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"XOX", "X O", "X O"},
                        {"   ", "   ", "   "},
                        {"O  ", "   ", "   "},
                        {"XOX", " X ", "   "},
                        {"XXX", "   ", "OOO"},
                        {"XOX", "O O", "XOX"}
                }
        );
        List<Boolean> results = Arrays.asList(
                true,
                true,
                false,
                false,
                false,
                true
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.validTicTacToe(tests.get(i)));
        }
    }
}