package leetcode.a105;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    TreeNode buildTree(int[] preorder, int pb, int pe, int[] inorder, int ib, int ie) {
        if(pb >= pe) {
            return null;
        }
        int mid = findMid(inorder, ib, ie, preorder[pb]);
        TreeNode root = new TreeNode(preorder[pb]);
        root.left = buildTree(preorder, pb + 1, pb + 1 + (mid - ib), inorder, ib, mid);
        root.right = buildTree(preorder, pb + 1 + (mid - ib), pe, inorder, mid + 1, ie);
        return root;
    }

    int findMid(int[] nums, int from, int to, int n) {
        for(; from < to; ++ from) {
            if(nums[from] == n) {
                return from;
            }
        }
        return -1;
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