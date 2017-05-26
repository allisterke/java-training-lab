package leetcode.a116;

import java.util.Arrays;
import java.util.List;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root != null) {
            root.next = null;
            while (root.left != null) {
                for(TreeLinkNode p = root; p != null; p = p.next) {
                    p.left.next = p.right;
                    p.right.next = p.next != null ? p.next.left : null;
                }
                root = root.left;
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