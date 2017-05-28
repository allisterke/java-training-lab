package leetcode.a144;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if(current.left == null) {
                result.add(current.val);
                current = current.right;
            }
            else {
                TreeNode previous = findPrevious(current);
                if(previous.right == null) {
                    result.add(current.val);
                    previous.right = current;
                    current = current.left;
                }
                else {
                    previous.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }

    TreeNode findPrevious(TreeNode root) {
        TreeNode previous = root.left;
        while (previous.right != null && previous.right != root) {
            previous = previous.right;
        }
        return previous;
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