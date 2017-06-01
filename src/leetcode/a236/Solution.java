package leetcode.a236;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    TreeNode lca = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca = null;
        find(root, p, q);
        return lca;
    }

    int find(TreeNode root, TreeNode p, TreeNode q) {
        if(root != null) {
            if(root == p) {
                if(find(root.left, q) || find(root.right, q)) {
                    lca = root;
                    return 2;
                }
                return 1;
            }
            else if(root == q) {
                if(find(root.left, p) || find(root.right, p)) {
                    lca = root;
                    return 2;
                }
                return 1;
            }
            else {
                int left = find(root.left, p, q);
                if(left == 2) {
                    return 2;
                }
                int right = find(root.right, p, q);
                if(right == 2) {
                    return 2;
                }
                int total = left + right;
                if(total == 2) {
                    lca = root;
                }
                return total;
            }
        }
        return 0;
    }

    boolean find(TreeNode root, TreeNode p) {
        if(root != null) {
            return root == p || find(root.left, p) || find(root.right, p);
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