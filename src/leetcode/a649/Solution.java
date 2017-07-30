package leetcode.a649;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    public String predictPartyVictory(String senate) {
        int r = 0, d = 0;
        for(int i = 0; i < senate.length(); ++ i) {
            if(senate.charAt(i) == 'R') {
                ++ r;
            }
            else {
                ++ d;
            }
        }
        while (r > 0 && d > 0) {
            boolean[] banned = new boolean[senate.length()];
            TreeSet<Integer> rt = new TreeSet<>();
            TreeSet<Integer> dt = new TreeSet<>();
            for(int i = 0; i < senate.length(); ++ i) {
                if(senate.charAt(i) == 'R') {
                    rt.add(i);
                }
                else {
                    dt.add(i);
                }
            }
            for(int i = 0; i < senate.length() && r > 0 && d > 0; ++ i) {
                if(!banned[i]) {
                    if(senate.charAt(i) == 'R') {
                        Integer next = dt.higher(i);
                        if(next == null) {
                            next = dt.first();
                        }
                        banned[next] = true;
                        dt.remove(next);
                        -- d;
                    }
                    else {
                        Integer next = rt.higher(i);
                        if(next == null) {
                            next = rt.first();
                        }
                        banned[next] = true;
                        rt.remove(next);
                        -- r;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < senate.length(); ++ i) {
                if(!banned[i]) {
                    sb.append(senate.charAt(i));
                }
            }
            senate = sb.toString();
        }
        return r > 0 ? "Radiant" : "Dire";
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