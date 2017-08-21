package leetcode.a661;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    Map<Integer, Long> levelMin;
    Map<Integer, Long> levelMax;
    public int widthOfBinaryTree(TreeNode root) {
        levelMin = new HashMap<>();
        levelMax = new HashMap<>();
        visit(root, 0, 0);
        int width = 0;
        for(int i = 0; levelMin.containsKey(i); ++ i) {
            width = Math.max(width, (int)(levelMax.get(i) - levelMin.get(i) + 1));
        }
        return width;
    }

    void visit(TreeNode root, int level, long index) {
        if(root != null) {
            levelMin.putIfAbsent(level, index);
            levelMax.putIfAbsent(level, index);
            levelMin.put(level, Math.min(levelMin.get(level), index));
            levelMax.put(level, Math.max(levelMax.get(level), index));

            visit(root.left, level + 1, index * 2 + 1);
            visit(root.right, level + 1, index * 2 + 2);
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