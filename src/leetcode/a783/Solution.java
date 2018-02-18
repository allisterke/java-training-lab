package leetcode.a783;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    TreeNode last;
    int minDistance;

    public int minDiffInBST(TreeNode root) {
        last = null;
        minDistance = Integer.MAX_VALUE;
        inOrder(root);
        return minDistance;
    }

    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);

        if (last != null) {
            minDistance = Math.min(minDistance, Math.abs(root.val - last.val));
        }
        last = root;

        inOrder(root.right);
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