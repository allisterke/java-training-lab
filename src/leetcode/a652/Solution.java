package leetcode.a652;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<Integer, List<TreeNode>> subtreeMap = new HashMap<>();
        collectSubtrees(root, subtreeMap);

        List<TreeNode> result = new ArrayList<>();
        for (List<TreeNode> subtrees : subtreeMap.values()) {
            Map<TreeNode, Integer> map = new HashMap<>();
            for (TreeNode subtree : subtrees) {
                boolean found = false;
                for (TreeNode node : map.keySet()) {
                    if (isSame(subtree, node)) {
                        found = true;
                        map.put(node, map.get(node) + 1);
                        break;
                    }
                }
                if (!found) {
                    map.put(subtree, 1);
                }
            }
            for(Map.Entry<TreeNode, Integer> entry : map.entrySet()) {
                if(entry.getValue() > 1) {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }

    boolean isSame(TreeNode a, TreeNode b) {
        if(a == null && b == null) {
            return true;
        }
        if(a != null && b != null && a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right)) {
            return true;
        }
        return false;
    }

    void collectSubtrees(TreeNode root, Map<Integer, List<TreeNode>> subtreeMap) {
        dfs(root, subtreeMap);
    }

    int dfs(TreeNode root, Map<Integer, List<TreeNode>> subtreeMap) {
        if(root != null) {
            int h1 = dfs(root.left, subtreeMap);
            int h2 = dfs(root.right, subtreeMap);
            int h = Math.max(h1, h2) + 1;
            subtreeMap.putIfAbsent(h, new ArrayList<>());
            subtreeMap.get(h).add(root);
            return h;
        }
        else {
            return 0;
        }
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