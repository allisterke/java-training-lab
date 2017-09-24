package leetcode.a684;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    Map<Integer, Integer> rootOf = new HashMap<>();
    public int[] findRedundantConnection(int[][] edges) {
        rootOf.clear();
        for(int[] edge : edges) {
            rootOf.putIfAbsent(edge[0], edge[0]);
            rootOf.putIfAbsent(edge[1], edge[1]);
        }
        for(int[] edge : edges) {
            if(getRootOf(edge[0]) != getRootOf(edge[1])) {
                merge(edge[0], edge[1]);
            }
            else {
                return edge;
            }
        }
        return null;
    }

    void merge(int n1, int n2) {
        rootOf.put(getRootOf(n1), getRootOf(n2));
    }

    int getRootOf(int n) {
        int root = rootOf.get(n);
        if(root != n) {
            root = getRootOf(root);
            rootOf.put(n, root);
        }
        return root;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {5, 2},
                                new int[] {3, 4},
                                new int[] {4, 3},
                                new int[] {5, 3},
                                new int[] {1, 3}
                        },
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {1, 3},
                                new int[] {2, 3}
                        },
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {1, 3},
                                new int[] {3, 1}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.findRedundantConnection(tests.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}