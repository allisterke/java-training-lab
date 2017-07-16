package leetcode.a638;

import java.util.*;

public class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        for(int i = 0; i < special.size(); ++ i) {
            List<Integer> s = special.get(i);
            List<Integer> comb = new ArrayList<>();
            for(int j = 0; j < s.size() - 1; ++ j) {
                comb.add(s.get(j));
            }
            Integer p = s.get(s.size() - 1);
            Integer q = map.get(comb);
            if(q == null || p < q) {
                map.put(comb, p);
            }
        }
        for(int i = 0; i < price.size(); ++ i) {
            List<Integer> comb = new ArrayList<>();
            for(int j = 0; j < price.size(); ++ j) {
                comb.add(0);
            }
            comb.set(i, 1);
            Integer p = price.get(i);
            Integer q = map.get(comb);
            if(q == null || p < q) {
                map.put(comb, p);
            }
        }
        int max = 0;
        for(int n : needs) {
            max = Math.max(max, n);
        }
        ++ max;

        int[] dp = new int[(int)Math.pow(max, price.size())];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Map.Entry<List<Integer>, Integer>[] entries = map.entrySet().toArray(
                new Map.Entry[map.size()]
        );
        for(int i = 1; i < dp.length; ++ i) {
            int[] indexList = toIndexList(i, price.size(), max);
            if(!less(indexList, needs)) {
                continue;
            }
            for(Map.Entry<List<Integer>, Integer> entry: entries) {
                boolean valid = true;
                int index = 0;
                for(int j = 0; j < price.size(); ++ j) {
                    if(indexList[j] >= entry.getKey().get(j)) {
                        index = index * max + indexList[j] - entry.getKey().get(j);

                    }
                    else {
                        valid = false;
                        break;
                    }
                }
                if(valid) {
                    dp[i] = Math.min(dp[i], dp[index] + entry.getValue());
                }
            }
        }
        return dp[toIndex(needs, max)];
    }

    boolean less(int[] a, List<Integer> b) {
        for(int i = 0; i < a.length; ++ i) {
            if(a[i] > b.get(i)) {
                return false;
            }
        }
        return true;
    }

    int[] toIndexList(int index, int n, int r) {
        int[] indexList = new int[n];
        for(int i = n-1; i >= 0; -- i) {
            indexList[i] = index % r;
            index /= r;
        }
        return indexList;
    }

    int toIndex(List<Integer> indexList, int r) {
        int index = 0;
        for(int i : indexList) {
            index = index * r + i;
        }
        return index;
    }

    int toIndex(int[] indexList, int r) {
        int index = 0;
        for(int i : indexList) {
            index = index * r + i;
        }
        return index;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        System.out.println(s.shoppingOffers(
                Arrays.asList(2, 5),
                Arrays.asList(
                        Arrays.asList(3, 0, 5),
                        Arrays.asList(1, 2, 10)
                ),
                Arrays.asList(3, 2)
        ));
        System.out.println(s.shoppingOffers(
                Arrays.asList(2, 3, 4),
                Arrays.asList(
                        Arrays.asList(1, 1, 0, 4),
                        Arrays.asList(2, 2, 1, 9)
                ),
                Arrays.asList(1, 2, 1)
        ));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}