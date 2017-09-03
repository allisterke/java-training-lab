package leetcode.a669;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) {
            return root;
        }
        if(root.val < L) {
            return trimBST(root.right, L, R);
        }
        else if(root.val > R) {
            return trimBST(root.left, L, R);
        }
        else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
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