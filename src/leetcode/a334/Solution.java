package leetcode.a334;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int a = 0, b = 0;
        int count = 0;
        for(int n : nums) {
            switch (count) {
                case 0:
                    a = n;
                    ++ count;
                    break;
                case 1:
                    if(n > a) {
                        b = n;
                        ++ count;
                    }
                    else {
                        a = n;
                    }
                    break;
                case 2:
                    if(n > b) {
                        return true;
                    }
                    else if(n > a) {
                        b = n;
                    }
                    else {
                        a = n;
                    }
                    break;
                default:
                    return false;
            }
        }
        return false;
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