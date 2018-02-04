package leetcode.a776;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null) {
            return new TreeNode[] { null, null };
        }
        if (root.val == V) {
            TreeNode[] result = new TreeNode[] { root, root.right };
            root.right = null;
            return result;
        }
        if (V < root.val) {
            TreeNode[] split = splitBST(root.left, V);
            root.left = split[1];
            return new TreeNode[] { split[0], root };
        }
        else {
            TreeNode[] split = splitBST(root.right, V);
            root.right = split[0];
            return new TreeNode[] { root, split[1] };
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