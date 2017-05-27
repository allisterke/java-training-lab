package leetcode.a129;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    int sumNumbers(TreeNode root, int current) {
        if(root != null) {
            current = current * 10 + root.val;
            if(root.left == null && root.right == null) {
                return current;
            }
            else {
                return sumNumbers(root.left, current) + sumNumbers(root.right, current);
            }
        }
        return 0;
    }

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
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