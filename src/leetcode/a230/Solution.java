package leetcode.a230;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        return inorder(root).val;
    }

    int k = 0;

    TreeNode inorder(TreeNode root) {
        if(root != null) {
            TreeNode left = inorder(root.left);
            if(left != null) {
                return left;
            }
            -- k;
            if(k == 0) {
                return root;
            }
            return inorder(root.right);
        }
        return null;
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