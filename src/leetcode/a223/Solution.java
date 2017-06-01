package leetcode.a223;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return getArea(A, B, C, D) + getArea(E, F, G, H)
                - getSharedArea(A, B, C, D, E, F, G, H);
    }

    int getArea(int A, int B, int C, int D) {
        return (C - A) * (D - B);
    }

    int getSharedArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int xr = getSharedRange(new int[][] {
                new int[] {A, 0},
                new int[] {C, 0},
                new int[] {E, 1},
                new int[] {G, 1}
        });
        int yr = getSharedRange(new int[][] {
                new int[] {B, 0},
                new int[] {D, 0},
                new int[] {F, 1},
                new int[] {H, 1}
        });
        return xr > 0 && yr > 0 ? xr * yr : 0;
    }

    int getSharedRange(int[][] axis) {
        Arrays.sort(axis, Comparator.comparingInt(a -> a[0]));
        return axis[0][1] == axis[1][1] ? -1 : axis[2][0] - axis[1][0];
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