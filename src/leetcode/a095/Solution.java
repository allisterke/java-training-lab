package leetcode.a095;

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

    TreeNode copy(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode copied = new TreeNode(root.val);
        copied.left = copy(root.left);
        copied.right = copy(root.right);
        return copied;
    }

    List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if(start >= end) {
            trees.add(null);
        }
        else {
            for(int i = start; i < end; ++ i) {
                List<TreeNode> leftList = generateTrees(start, i);
                List<TreeNode> rightList = generateTrees(i + 1, end);
                for(TreeNode left : leftList) {
                    for(TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = copy(left);
                        root.right = copy(right);
                        trees.add(root);
                    }
                }
            }
        }
        return trees;
    }

    public List<TreeNode> generateTrees(int n) {
        if(n <= 0) {
            return Arrays.asList();
        }
        return generateTrees(1, n+1);
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