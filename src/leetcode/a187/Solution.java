package leetcode.a187;

import java.util.*;

public class Solution {
    static long MOD = 26L * 26L * 26L * 26L * 26L * 26L * 26L * 26L * 26L;
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if(s.length() >= 10) {
            Map<Long, Integer> countMap = new HashMap<>();
            long n = 0;
            for(int i = 0; i < 9; ++ i) {
                n = n * 26 + s.charAt(i) - 'A';
            }
            for(int i = 9; i < s.length(); ++ i) {
                n %= MOD;
                n = n * 26 + s.charAt(i) - 'A';
                countMap.putIfAbsent(n, 0);
                countMap.put(n, countMap.get(n) + 1);
            }
            for(Map.Entry<Long, Integer> entry : countMap.entrySet()) {
                if(entry.getValue() > 1) {
                    result.add(buildDnaString(entry.getKey()));
                }
            }
        }
        return result;
    }

    StringBuilder sb = new StringBuilder();

    String buildDnaString(long n) {
        sb.setLength(0);
        for(int i = 0; i < 10; ++ i) {
            sb.append((char)('A' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findRepeatedDnaSequences(tests.get(i)));
        }
    }
}