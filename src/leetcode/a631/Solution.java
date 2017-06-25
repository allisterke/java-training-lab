package leetcode.a631;

import java.util.Arrays;
import java.util.List;

class Excel {

    static abstract class Cell {
        public abstract int getValue();
    }

    static class PlainCell extends Cell {
        int value;

        @Override
        public int getValue() {
            return value;
        }

        public PlainCell(int v) {
            super();
            value = v;
        }
    }

    static class SumCell extends Cell {
        Cell[][] board;
        int[] r1, r2, c1, c2;
        public SumCell(Cell[][] board, String[] ranges) {
            this.board = board;

            r1 = new int[ranges.length];
            r2 = new int[ranges.length];
            c1 = new int[ranges.length];
            c2 = new int[ranges.length];
            for(int i = 0; i < ranges.length; ++ i) {
                String[] range = ranges[i].split(":");
                c1[i] = range[0].charAt(0) - 'A';
                r1[i] = range[0].charAt(1) - '0';
                if(range[0].length() > 2) {
                    r1[i] = r1[i] * 10 + (range[0].charAt(2) - '0');
                }
                r1[i] -= 1;
                if (range.length > 1) {
                    c2[i] = range[1].charAt(0) - 'A';
                    r2[i] = range[1].charAt(1) - '0';
                    if(range[1].length() > 2) {
                        r2[i] = r2[i] * 10 + (range[1].charAt(2) - '0');
                    }
                    r2[i] -= 1;
                } else {
                    r2[i] = r1[i];
                    c2[i] = c1[i];
                }
            }
        }

        @Override
        public int getValue() {
            int sum = 0;
            for(int i = 0; i < r1.length; ++ i) {
                for (int r = r1[i]; r <= r2[i]; ++r) {
                    for (int c = c1[i]; c <= c2[i]; ++c) {
                        sum += board[r][c].getValue();
                    }
                }
            }
            return sum;
        }
    }

    static class CellFactory {
        static public Cell newPlainCell(int v) {
            return new PlainCell(v);
        }

        static public Cell newSumCell(Cell[][] board, String[] ranges) {
            return new SumCell(board, ranges);
        }
    }

    Cell[][] board = new Cell[26][26];

    public Excel(int H, char W) {
        for(int i = 0; i < board.length; ++ i) {
            for(int j = 0; j < board[i].length; ++ j) {
                board[i][j] = CellFactory.newPlainCell(0);
            }
        }
    }

    public void set(int r, char c, int v) {
        board[r - 1][c - 'A'] = CellFactory.newPlainCell(v);
    }

    public int get(int r, char c) {
        return board[r - 1][c - 'A'].getValue();
    }

    public int sum(int r, char c, String[] strs) {
        board[r - 1][c - 'A'] = CellFactory.newSumCell(board, strs);
        return board[r - 1][c - 'A'].getValue();
    }
}

public class Solution {


    public static void main(String[] args) {
//        test1();
        test2();
    }

    static void test1() {
        Excel excel = new Excel(3, 'C');
        excel.sum(1, 'A', new String[] {"A2"});
        excel.set(2, 'A', 1);
        System.out.println(excel.get(1, 'A'));
    }

    static void test2() {
        Excel excel = new Excel(26, 'Z');
        excel.set(1, 'A', 1);
        excel.set(1, 'I', 1);
        System.out.println(excel.sum(7, 'D', new String[] {"A1:D6","A1:G3","A1:C12"}));
        System.out.println(excel.sum(10, 'G', new String[] {"A1:D7","D1:F10","D3:I8","I1:I9"}));
    }
}