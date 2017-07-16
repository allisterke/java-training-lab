package leetcode.a637;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    List<Double> levelSum;
    List<Integer> levelCount;
    public List<Double> averageOfLevels(TreeNode root) {
        levelSum = new ArrayList<>();
        levelCount = new ArrayList<>();

        dfs(root, 0);

        for(int i = 0; i < levelSum.size(); ++ i) {
            levelSum.set(i, levelSum.get(i) / levelCount.get(i));
        }

        return levelSum;
    }

    void dfs(TreeNode root, int level) {
        if(root != null) {
            if(level >= levelSum.size()) {
                levelSum.add((double)root.val);
                levelCount.add(1);
            }
            else {
                levelSum.set(level, levelSum.get(level) + root.val);
                levelCount.set(level, levelCount.get(level) + 1);
            }
            dfs(root.left, level + 1);
            dfs(root.right, level + 1);
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