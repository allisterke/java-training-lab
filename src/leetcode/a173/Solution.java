package leetcode.a173;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class BSTIterator {

    TreeNode current;

    public BSTIterator(TreeNode root) {
        current = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return current != null;
    }

    /** @return the next smallest number */
    public int next() {
        while (current != null) {
            if(current.left == null) {
                try {
                    return current.val;
                } finally {
                    current = current.right;
                }
            }
            else {
                TreeNode previous = findPrevious(current);
                if(previous.right == null) {
                    previous.right = current;
                    current = current.left;
                }
                else {
                    previous.right = current;
                    try {
                        return current.val;
                    }
                    finally {
                        current = current.right;
                    }
                }
            }
        }
        return -1;
    }

    TreeNode findPrevious(TreeNode root) {
        TreeNode previous = root.left;
        while (previous.right != null && previous.right != root) {
            previous = previous.right;
        }
        return previous;
    }
}

public class Solution {


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