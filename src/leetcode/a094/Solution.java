package leetcode.a094;

import sun.reflect.generics.tree.Tree;

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if(current.left == null) {
                inorder.add(current.val);
                current = current.right;
            }
            else {
                TreeNode previous = findPrevious(current);
                if(previous.right == null) {
                    previous.right = current;
                    current = current.left;
                }
                else {
                    previous.right = null; // restore
                    inorder.add(current.val);
                    current = current.right;
                }
            }
        }
        return inorder;
    }

    TreeNode findPrevious(TreeNode current) {
        TreeNode previous = current.left;
        while (previous.right != null && previous.right != current) {
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