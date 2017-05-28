package leetcode.a199;

import sun.reflect.generics.tree.Tree;

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> currentLevel = new ArrayList<>();
        if(root != null) {
            currentLevel.add(root);
        }
        while (!currentLevel.isEmpty()) {
            result.add(currentLevel.get(currentLevel.size() - 1).val);
            List<TreeNode> nextLevel = new ArrayList<>();
            for(TreeNode current : currentLevel) {
                if(current.left != null) {
                    nextLevel.add(current.left);
                }
                if(current.right != null) {
                    nextLevel.add(current.right);
                }
            }
            currentLevel = nextLevel;
        }
        return result;
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