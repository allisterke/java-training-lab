package leetcode.a691;

import java.util.*;

public class Solution {
    static class AlphaCount {
        int[] a = new int[26];

        public AlphaCount() {

        }

        public AlphaCount(String s) {
            for(int i = 0; i < s.length(); ++ i) {
                ++ a[s.charAt(i) - 'a'];
            }
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof AlphaCount && Arrays.equals(a, ((AlphaCount)obj).a);
        }
    }

    AlphaCount[] acs;
    Map<AlphaCount, Integer> cache;

    public int minStickers(String[] stickers, String target) {
        acs = new AlphaCount[stickers.length];
        for(int i = 0; i < stickers.length; ++ i) {
            acs[i] = new AlphaCount(stickers[i]);
        }
        int[] owner = new int[26];
        for(int i = 0; i < stickers.length; ++ i) {
            for(int j = 0; j < 26; ++ j) {
                if(acs[i].a[j] > 0) {
                    ++ owner[j];
                }
            }
        }
        AlphaCount tac = new AlphaCount(target);

        int precount = 0;
        for(int i = 0; i < 26; ++ i) {
            if(tac.a[i] > 0) {
                if(owner[i] == 0) {
                    return -1;
                }
                else if(owner[i] == 1) {
                    int k = 0;
                    for(; acs[k].a[i] == 0; ++ k) ;
                    int r = (tac.a[i] - 1) / acs[k].a[i] + 1;
                    precount += r;
                    for(int j = 0; j < 26; ++ j) {
                        tac.a[j] = Math.max(tac.a[j] - acs[k].a[j] * r, 0);
                    }
                }
            }
        }

        cache = new HashMap<>();
        return precount + minStickers(tac);
    }

    int minStickers(AlphaCount tac) {
        if(!cache.containsKey(tac)) {
            int sum = 0;
            for(int n : tac.a) {
                sum += n;
            }
            if(sum == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for(AlphaCount ac : acs) {
                AlphaCount tmp = new AlphaCount();
                for(int i = 0; i < 26; ++ i) {
                    tmp.a[i] = Math.max(tac.a[i] - ac.a[i], 0);
                }
                if(!tmp.equals(tac)) {
                    int ss = minStickers(tmp);
                    min = Math.min(min, ss + 1);
                }
            }
            cache.put(tac, min);
        }
        return cache.get(tac);
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        new String[] {"with", "example", "science"},
                        new String[] {"notice", "possible"}
                }
        );
        List<String> targets = Arrays.asList(
                "thehat",
                "basicbasic"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minStickers(tests.get(i), targets.get(i)));
        }
    }
}