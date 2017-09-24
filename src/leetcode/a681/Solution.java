package leetcode.a681;

import java.util.*;

public class Solution {
    public String nextClosestTime(String time) {
        Set<Integer> set = new TreeSet<>();
        for(char c : time.toCharArray()) {
            if(Character.isDigit(c)) {
                set.add(c - '0');
            }
        }
        int M = ((time.charAt(0) - '0') * 10 + time.charAt(1) - '0')*60 + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
        int N = M;
        int D = 0;
        List<Integer> list = new ArrayList<>(set);
//        List<String> cache = new ArrayList<>();
        for (int i = 0; i < Math.pow(list.size(), 4); ++ i) {
            int t = i;
            List<Integer> hm = new ArrayList<>();
            for(int j = 0; j < 4; ++ j) {
                hm.add(list.get(t % list.size()));
                t /= list.size();
            }
            int hour = hm.get(0) * 10 + hm.get(1);
            int minute = hm.get(2) * 10 + hm.get(3);
//            cache.add(String.format("%02d:%02d", hour, minute));
            if(hour >= 24 || minute >= 60) {
                continue;
            }
            int N1 = hour * 60 + minute;
            int D1 = N1 > M ? N1 - M : 24*60 + (N1 - M);
            if(N == M || D1 < D) {
                N = N1;
                D = D1;
            }
        }
//        Collections.sort(cache);
//        System.out.println(cache.size());
//        for(String c : cache) {
//            System.out.println(c);
//        }
        return String.format("%02d:%02d", N/60, N%60);
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "19:34",
                "23:59"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.nextClosestTime(tests.get(i)));
        }
    }
}