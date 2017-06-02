package leetcode.a275;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int hIndex(int[] citations) {
        if(citations.length == 0 || citations[citations.length - 1] <= 0) {
            return 0;
        }
        if(citations[0] >= citations.length) {
            return citations.length;
        }
        int left = 0, right = citations.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if(citations[citations.length - mid] >= mid) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right - 1;
    }

    public int hIndex0(int[] citations) {
        int sum = 0;
        for(int h = citations.length, i = citations.length - 1; h > 0; -- h) {
            for(; i >= 0 && citations[i] >= h; ++ sum, -- i)
                ;
            if(sum >= h) {
                return h;
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