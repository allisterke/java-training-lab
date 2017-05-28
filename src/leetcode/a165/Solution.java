package leetcode.a165;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int getVersion(String[] v, int i) {
        return i < v.length ? Integer.valueOf(v[i]) : 0;
    }
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for(int i = 0; i < Math.max(v1.length, v2.length); ++ i) {
            int p1 = getVersion(v1, i);
            int p2 = getVersion(v2, i);
            if(p1 != p2) {
                return Integer.compare(p1, p2);
            }
        }
        return 0;
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