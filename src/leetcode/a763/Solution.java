package leetcode.a763;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] lastPosition = new int[26];
        Arrays.fill(lastPosition, -1);
        for(int i = 0; i < S.length(); ++ i) {
            lastPosition[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partitions = new ArrayList<>();
        for (int i = 0; i < S.length(); ) { // todo next i
            int next = i+1;
            for (int j = i; j < next; ++ j) {
                int index = S.charAt(j) - 'a';
                next = Math.max(next, lastPosition[index] + 1);
            }
            partitions.add(next - i);
            i = next;
        }
        return partitions;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "ababcbacadefegdehijhklij",
                "aabbcc"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.partitionLabels(tests.get(i)));
        }
    }
}