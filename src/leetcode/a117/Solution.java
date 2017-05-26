package leetcode.a117;

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
            TreeLinkNode dummy = new TreeLinkNode(0);
            root.next = null;
            while (root != null) {
                TreeLinkNode previous = dummy;
                for (TreeLinkNode p = root; p != null; p = p.next) {
                    if(p.left != null) {
                        previous.next = p.left;
                        previous = p.left;
                    }
                    if(p.right != null) {
                        previous.next = p.right;
                        previous = p.right;
                    }
                }
                previous.next = null;
                root = dummy.next;
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