package leetcode.a751;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> ipToCIDR(String ip, int range) {
        List<String> result = new ArrayList<>();
        long n = ip2i(ip);
        while (range > 0) {
            int i = 0;
            int mask = 0;
            while (true) {
                int ti = i + 1;
                int tm = (mask << 1) | 1;
                if((n & tm) != 0 || (1 << ti) > range) {
                    break;
                }
                i = ti;
                mask = tm;
            }
            result.add(i2ip(n) + "/" + (32 - i));
            n += (1 << i);
            range -= (1 << i);
        }
        return result;
    }

    String i2ip(long n) {
        long[] parts = new long[4];
        for (int i = 0; i < 4; ++ i) {
            parts[3 - i] = n & 0xff;
            n >>= 8;
        }
        StringBuilder builder = new StringBuilder();
        for (long p : parts) {
            builder.append(p);
            builder.append('.');
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    long ip2i(String ip) {
        String[] parts = ip.split("\\.");
        long n = 0;
        for (int i = 0; i < parts.length; ++ i) {
            n = n * 256 + Integer.valueOf(parts[i]);
        }
        return n;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "255.0.0.7",
                "1.20.14.31"
        );
        List<Integer> range = Arrays.asList(
                10,
                208
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.ipToCIDR(tests.get(i), range.get(i)));
        }
    }
}