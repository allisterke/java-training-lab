package leetcode.a632;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int[][] a = new int[nums.size()][];
        for (int i = 0; i < a.length; ++ i) {
            a[i] = new int[nums.get(i).size()];
            for(int j = 0; j < a[i].length; ++ j) {
                a[i][j] = nums.get(i).get(j);
            }
        }
        return smallestRange(a);
    }
    public int[] smallestRange(int[][] nums) {
        int total = 0;
        for(int [] a : nums) {
            total += a.length;
        }
        int[] all = new int[total];
        int offset = 0;
        for (int[] a : nums) {
            for(int ai : a) {
                all[offset ++] = ai;
            }
        }
        Arrays.sort(all);
        int minMax = (int)1e5;
        int maxMin = -(int)1e5;
        for (int[] a : nums) {
            minMax = Math.min(minMax, a[a.length - 1]);
            maxMin = Math.max(maxMin, a[0]);
        }

        int last = -(int)1e5 - 1;
        int lower = -(int)1e5, upper = (int)1e5;
        for(int i = 0; i <= Arrays.binarySearch(all, minMax); ++ i) {
            int a = all[i];
            if(a != last) {
                last = a;
                int b = upperBound(nums, a);
                if(b - a < upper - lower || b - a == upper - lower && a < lower) {
                    lower = a;
                    upper = b;
                }
            }
        }
        return new int[] {lower, upper};
    }

    int upperBound(int[][] nums, int left) {
        int lower = left, upper = (int)1e5;
        while (lower < upper) {
            int mid = lower + (upper - lower) / 2;
            boolean valid = true;
            for(int[] a : nums) {
                int index = Arrays.binarySearch(a, left);
                if(index >= 0) {
                    continue;
                }
                if(Arrays.binarySearch(a, mid) == index) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                upper = mid;
            }
            else {
                lower = mid + 1;
            }
        }
        return upper;
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> tests = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(1, 2, 3)
                ),
                Arrays.asList(
                        Arrays.asList(-5, -4, -3, -2, -1),
                        Arrays.asList(1,2,3,4,5)
                ),
                Arrays.asList(
                        Arrays.asList(4,10,15,24,26),
                        Arrays.asList(0,9,12,20),
                        Arrays.asList(5,18,22,30)
                )
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.smallestRange(tests.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}