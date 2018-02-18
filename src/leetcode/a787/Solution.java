package leetcode.a787;

import java.util.*;

public class Solution {
    Map<List<Integer>, Integer> cache;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> flightMap = new HashMap<>();
        for (int[] flight : flights) {
            if (!flightMap.containsKey(flight[0])) {
                flightMap.put(flight[0], new HashMap<>());
            }
            flightMap.get(flight[0]).put(flight[1], flight[2]);
        }
        cache = new HashMap<>();
        return findCheapestPrice(flightMap, src, dst, K);
    }

    int findCheapestPrice(Map<Integer, Map<Integer, Integer>> flightMap, int src, int dst, int K) {
        if(src == dst) {
            return 0;
        }
        if(!flightMap.containsKey(src)) {
            return -1;
        }
        if (K == 0) {
            if(!flightMap.get(src).containsKey(dst)) {
                return -1;
            }
            return flightMap.get(src).get(dst);
        }
        List<Integer> key = Arrays.asList(src, K);
        if(!cache.containsKey(key)) {
            int cheapest = -1;
            for (Map.Entry<Integer, Integer> entry : flightMap.get(src).entrySet()) {
                int price = findCheapestPrice(flightMap, entry.getKey(), dst, K - 1);
                if (price < 0) {
                    continue;
                }
                if (cheapest < 0 || cheapest > price + entry.getValue()) {
                    cheapest = price + entry.getValue();
                }
            }
            cache.put(key, cheapest);
        }
        return cache.get(key);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findCheapestPrice(3, new int[][] {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
    }
}