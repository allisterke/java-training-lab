package leetcode.a733;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] != newColor) {
            floodFill(image, sr, sc, image[sr][sc], newColor);
        }
        return image;
    }

    void floodFill(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != oldColor) {
            return;
        }
        image[sr][sc] = newColor;
        floodFill(image, sr+1, sc, oldColor, newColor);
        floodFill(image, sr-1, sc, oldColor, newColor);
        floodFill(image, sr, sc+1, oldColor, newColor);
        floodFill(image, sr, sc-1, oldColor, newColor);
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