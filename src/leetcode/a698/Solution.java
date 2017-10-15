package leetcode.a698;

import java.util.*;

public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        int avg = sum / k;
        if(sum % k != 0 || Arrays.stream(nums).max().getAsInt() > avg) {
            return false;
        }
        List<List<Integer>> combs = collect(avg, nums);
        if(combs.size() < k) {
            return false;
        }
        combs.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); ++ i) {
                if(a.get(i) != b.get(i)) {
                    return Integer.compare(a.get(i), b.get(i));
                }
            }
            return Integer.compare(a.size(), b.size());
        });
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) {
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }
        return iterate(combs, map, k, 0);
    }

    boolean iterate(List<List<Integer>> combs, Map<Integer, Integer> map, int k, int start) {
        if(k <= 0) {
            return true;
        }
        for (int i = start; i < combs.size(); ++ i) {
            List<Integer> comb = combs.get(i);
            boolean valid = true;
            for(int n : comb) {
                if (map.getOrDefault(n, 0) < 1) {
                    valid = false;
                    break;
                }
            }
            if(!valid) {
                continue;
            }
            for(int n : comb) {
                map.put(n, map.get(n) - 1);
            }
            if(iterate(combs, map, k-1, i+1)) {
                return true;
            }

            for(int n : comb) {
                map.put(n, map.get(n) + 1);
            }
        }
        return false;
    }

    List<List<Integer>> collect(int avg, int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        map.put(0, Arrays.asList(Arrays.asList()));
        for (int n : nums) {
            Map<Integer, List<List<Integer>>> nm = new HashMap<>();
            for(Map.Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
                int nk = entry.getKey() + n;
                if(nk > avg) {
                    continue;
                }
                if(!nm.containsKey(nk)) {
                    nm.put(nk, new ArrayList<>());
                }
                List<List<Integer>> combs = nm.get(nk);
                for (List<Integer> list : entry.getValue()) {
                    List<Integer> nl = new ArrayList<>(list);
                    nl.add(n);
                    combs.add(nl);
                }
            }
            for(Map.Entry<Integer, List<List<Integer>>> entry : nm.entrySet()) {
                if(map.containsKey(entry.getKey())) {
                    map.get(entry.getKey()).addAll(entry.getValue());
                }
                else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return map.getOrDefault(avg, Arrays.asList());
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {4, 3, 2, 3, 5, 2, 1}
                }
        );
        List<Integer> ks = Arrays.asList(
                4
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.canPartitionKSubsets(tests.get(i), ks.get(i)));
        }
    }
}