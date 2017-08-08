package leetcode.a653;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    TreeNode root;
    public boolean findTarget(TreeNode root, int k) {
        this.root = root;
        return inOrderSearch(root, k);
    }

    boolean inOrderSearch(TreeNode node, int k) {
        if(node != null) {
            if(inOrderSearch(node.left, k) ||
                    node.val * 2 != k && binarySearch(root, k - node.val) ||
                    inOrderSearch(node.right, k)) {
                return true;
            }
        }
        return false;
    }

    boolean binarySearch(TreeNode root, int n) {
        if(root != null) {
            if(n < root.val) {
                return binarySearch(root.left, n);
            }
            else if(n > root.val) {
                return binarySearch(root.right, n);
            }
            else {
                return true;
            }
        }
        return false;
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