package leetcode.a365;

import java.util.*;

public class Solution {
    int gcd(int x, int y) {
        if(y == 0) {
            return x;
        }
        return x >= y ? gcd(y, x%y) : gcd(y, x);
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if(z > x + y) {
            return false;
        }
        if(z == 0) {
            return true;
        }
        if(x == 0 || y == 0) {
            return z == x + y;
        }
        int g = gcd(x, y);
        return z % g == 0;
    }

    public boolean canMeasureWater0(int x, int y, int z) {
        Set<List<Integer>> visited = new HashSet<>();
        Map<List<Integer>, List<Integer>> fromMap = new HashMap<>();
        Queue<Integer> jx = new ArrayDeque<>();
        Queue<Integer> jy = new ArrayDeque<>();
        jx.add(0);
        jy.add(0);
        visited.add(Arrays.asList(0, 0));
        while (!jx.isEmpty()) {
            int i = jx.poll();
            int j = jy.poll();
            if(i + j == z) {
//                List<Integer> current = Arrays.asList(i, j);
//                while (current != null) {
//                    System.out.println(current);
//                    current = fromMap.get(current);
//                }
                return true;
            }
            List<List<Integer>> nextStatus = Arrays.asList(
                    Arrays.asList(x, j),
                    Arrays.asList(i, y),
                    Arrays.asList(x != 0 ? (i+j)%x : 0, 0),
                    Arrays.asList(0, y != 0 ? (i+j)%y : 0)
            );
            for(List<Integer> status: nextStatus) {
                if(!visited.contains(status)) {
                    fromMap.putIfAbsent(status, Arrays.asList(i, j));
                    visited.add(status);
                    jx.add(status.get(0));
                    jy.add(status.get(1));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int limit = 1000;
        List<List<Integer>> tests = Arrays.asList(
//                Arrays.asList(22003, 31237, 1)
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit)),
                Arrays.asList(r.nextInt(limit), r.nextInt(limit), r.nextInt(limit))
//                Arrays.asList(18, 21, 5)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(tests.get(i));
            System.out.println(
                    s.canMeasureWater(tests.get(i).get(0), tests.get(i).get(1), tests.get(i).get(2)) ==
                    s.canMeasureWater(tests.get(i).get(0), tests.get(i).get(1), tests.get(i).get(2)));
        }
    }
}