package leetcode.a755;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; ++ i) {
            int j = K;
            for(; j-1 >= 0 && heights[j-1] <= heights[j]; -- j);
            if(heights[j] < heights[K]) {
                for (; heights[j+1] == heights[j]; ++ j);
                ++ heights[j];
                continue;
            }
            j = K;
            for(; j+1 < heights.length && heights[j+1] <= heights[j]; ++ j);
            if(heights[j] < heights[K]) {
                for (; heights[j-1] == heights[j]; -- j);
                ++ heights[j];
                continue;
            }
            ++ heights[K];
        }
        return heights;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {2,1,1,2,1,2,2}
                }
        );
        List<int[]> params = Arrays.asList(
                new int[][] {
                        {4, 3}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.pourWater(tests.get(i), params.get(i)[0], params.get(i)[1])).boxed().collect(Collectors.toList()));
        }
    }
}