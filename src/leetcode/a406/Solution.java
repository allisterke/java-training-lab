package leetcode.a406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? - Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        List<int[]> queue = new ArrayList<>();
        for(int[] person : people) {
            queue.add(person[1], person);
        }
        return queue.toArray(new int[queue.size()][]);
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