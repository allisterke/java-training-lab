package leetcode.a106;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode buildTree(int[] inorder, int ib, int ie, int[] postorder, int pb, int pe) {
        if(ib >= ie) {
            return null;
        }
        int mid = findMid(inorder, ib, ie, postorder[pe - 1]);
        TreeNode root = new TreeNode(postorder[pe - 1]);
        root.left = buildTree(inorder, ib, mid, postorder, pb, pb + (mid - ib));
        root.right = buildTree(inorder, mid + 1, ie, postorder, pb + (mid - ib), pe - 1);
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