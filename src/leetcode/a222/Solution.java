package leetcode.a222;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int countNodes(TreeNode root) {
//        int ld = leftDepth(root);
//        int rd = rightDepth(root);
//        return ld == rd ? (1 << ld) - 1 : countNodes(root.left) + countNodes(root.right) + 1;
        return countNodes(root, -1, -1);
    }

    int countNodes(TreeNode root, int ld, int rd) {
        if(ld < 0) {
            ld = leftDepth(root);
        }
        if(rd < 0) {
            rd = rightDepth(root);
        }
        if(ld == rd) {
            return (1 << ld) - 1;
        }
        else {
            return countNodes(root.left, ld - 1, -1) + countNodes(root.right, -1, rd - 1) + 1;
        }
    }

    int leftDepth(TreeNode root) {
        int depth = 0;
        for(; root != null; root = root.left) {
            ++ depth;
        }
        return depth;
    }
    int rightDepth(TreeNode root) {
        int depth = 0;
        for(; root != null; root = root.right) {
            ++ depth;
        }
        return depth;
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