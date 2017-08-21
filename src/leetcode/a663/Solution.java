package leetcode.a663;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    boolean found = false;
    int givenSum = 0;

    public boolean checkEqualTree(TreeNode root) {
        if(root == null || root.left == null && root.right == null) {
            return false;
        }
        int sum = sum(root);
        if(sum % 2 != 0) {
            return false;
        }

        found = false;
        givenSum = sum / 2;

        findSubtreeOfGivenSum(root.left);
        if(found) {
            return true;
        }
        findSubtreeOfGivenSum(root.right);
        if(found) {
            return true;
        }
        return false;
    }

    int findSubtreeOfGivenSum(TreeNode root) {
        if(root != null) {
            int left = sum(root.left);
            if (found) {
                return 0;
            }
            int right = sum(root.right);
            if(found) {
                return 0;
            }
            int sum = left + right + root.val;
            if(sum == givenSum) {
                found = true;
            }
            return sum;
        }
        return 0;
    }

    int sum(TreeNode root) {
        if(root != null) {
            return sum(root.left) + sum(root.right) + root.val;
        }
        else {
            return 0;
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