package leetcode.a654;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int begin, int end) {
        if(begin >= end) {
            return null;
        }
        int maxIndex = begin;
        for(int i = begin+1; i < end; ++ i) {
            if(nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, begin, maxIndex);
        node.right = constructMaximumBinaryTree(nums, maxIndex+1, end);
        return node;
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