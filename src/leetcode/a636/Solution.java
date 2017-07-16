package leetcode.a636;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> tmap;
    int offset;
    public int[] exclusiveTime(int n, List<String> logs) {
        tmap = new HashMap<>();
        offset = 0;
        while (offset < logs.size()) {
            exclusiveTime(logs);
        }
        return toArray(n, tmap);
    }

    int[] toArray(int n, Map<Integer, Integer> tmap) {
        int[] a = new int[n];
        for(Map.Entry<Integer, Integer> entry : tmap.entrySet()) {
            a[entry.getKey()] = entry.getValue();
        }
        return a;
    }

    int exclusiveTime(List<String> logs) {
        List<Integer> start = extract(logs.get(offset ++));

        int recursive = 0;
        while (true) {
            List<Integer> next = extract(logs.get(offset));
            if(next.get(1) == 1) {
                ++ offset;
                int cost = next.get(2) - start.get(2) + 1;
                tmap.putIfAbsent(start.get(0), 0);
                tmap.put(start.get(0), tmap.get(start.get(0)) + cost - recursive);
                return cost;
            }
            else {
                recursive += exclusiveTime(logs);
            }
        }
    }

    List<Integer> extract(String log) {
        String[] parts = log.split(":");
        return Arrays.asList(Integer.valueOf(parts[0]), parts[1].equals("start") ? 0 : 1, Integer.valueOf(parts[2]));
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                2,
                1
        );
        List<List<String>> logs = Arrays.asList(
                Arrays.asList(
                                "0:start:0",
                                "1:start:2",
                                "1:end:5",
                                "0:end:6"
                ),
                Arrays.asList(
                        "0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"
                )
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            for (int a: s.exclusiveTime(tests.get(i), logs.get(i))) {
                System.out.println(a);
            }
        }
    }
}