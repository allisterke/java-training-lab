package leetcode.a623;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            root = node;
        }
        else {
            addOneRow(root, 1, v, d-1);
        }
        return root;
    }

    void addOneRow(TreeNode root, int h, int v, int d) {
        if(root != null) {
            if(h < d) {
                addOneRow(root.left, h+1, v, d);
                addOneRow(root.right, h+1, v, d);
            }
            else if (h == d) {
                TreeNode left = new TreeNode(v);
                TreeNode right = new TreeNode(v);
                left.left = root.left;
                right.right = root.right;
                root.left = left;
                root.right = right;
            }
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