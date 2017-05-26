package leetcode.a114;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public void flatten(TreeNode root) {
        flattenReturningTail(root);
    }

    TreeNode flattenReturningTail(TreeNode root) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null) {
            return root;
        }
        if(root.right == null) {
            root.right = root.left;
            root.left = null;
        }
        if(root.left == null) {
            return flattenReturningTail(root.right);
        }
        else {
            TreeNode tail1 = flattenReturningTail(root.left);
            TreeNode tail2 = flattenReturningTail(root.right);
            tail1.right = root.right;
            root.right = root.left;
            root.left = null;
            return tail2;
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