package leetcode.a670;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maximumSwap(int num) {
        StringBuilder builder = new StringBuilder();
        builder.append(num);
        for(int i = 0; i < builder.length(); ++ i) {
            for(int j = i+1; j < builder.length(); ++ j) {
                char tmp = builder.charAt(i);
                builder.setCharAt(i, builder.charAt(j));
                builder.setCharAt(j, tmp);

                num = Math.max(num, Integer.valueOf(builder.toString()));

                tmp = builder.charAt(i);
                builder.setCharAt(i, builder.charAt(j));
                builder.setCharAt(j, tmp);
            }
        }
        return num;
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