package leetcode.a753;

import java.util.*;

public class Solution {
    Map<Integer, List<Integer>> map;
    int mod;
    Set<Integer> visited;
    StringBuilder builder;
    int length;

    public String crackSafe(int n, int k) {
        map = new HashMap<>();
        mod = (int)(Math.pow(10, n-1));
        visited = new HashSet<>();
        builder = new StringBuilder();
        length = n;
        rloop(1, 0, n, k);
        visited.add(0);
        for (int i = 0; i < n-1; ++ i) {
            builder.append(0);
        }
        dfs(0);
        return builder.toString();
    }

    boolean dfs(int n) {
        builder.append(n % 10);
        visited.add(n);
        boolean tried = false;
        for (int i: map.get(n)) {
            if(visited.contains(i)) {
                continue;
            }
            tried = true;
            if(dfs(i)) {
                return true;
            }
        }
        if(!tried) {
            if(builder.length() - (length - 1) == map.size()) {
                return true;
            }
        }
        builder.setLength(builder.length() - 1);
        visited.remove(n);
        return false;
    }

    int checkBreadth(int n) {
        int count = 0;
        for (int i : map.get(n)) {
            if(!visited.contains(i)) {
                ++ count;
            }
        }
        return count;
    }

    void rloop(int depth, int num, int n, int k) {
        for (int i = 0; i < k; ++ i) {
            int nn = num * 10 + i;
            if(depth != n) {
                rloop(depth + 1, nn, n, k);
            }
            else {
                map.put(nn, new ArrayList<>());
                for (int j = 0; j < k; ++ j) {
                    map.get(nn).add(nn % mod * 10 + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 2},
                        {2, 2},
                        {3, 2},
                        {4, 8}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.crackSafe(tests.get(i)[0], tests.get(i)[1]));
        }
    }
}