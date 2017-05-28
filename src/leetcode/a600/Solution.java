package leetcode.a600;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findIntegers(int num) {
        int[] f = new int[40];
        f[0] = f[1] = 1;
        for(int i = 2; i < f.length; ++ i) {
            f[i] = f[i-1] + f[i-2];
        }

        int count = 0;
        for(int i = 30; i >= 0; -- i) {
            if((num & (1 << i)) != 0) {
                if(i == 0) {
                    count += 2;
                }
                else {
                    count += f[i + 1];
                    if ((num & (1 << (i - 1))) != 0) {
                        boolean set = false;
                        for (int j = i - 1; j >= 0; --j) {
                            num ^= (num & (1 << j));
                            if (set) {
                                num |= (1 << j);
                            }
                            set = !set;
                        }
                    }
                }
            }
            else if(i == 0) {
                ++ count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                5,
                3,
                10
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findIntegers(tests.get(i)));
        }
    }
}