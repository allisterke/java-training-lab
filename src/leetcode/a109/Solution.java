package leetcode.a109;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    ListNode findMid(ListNode head) {
        ListNode fast = head.next.next;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode mid = findMid(head);
        TreeNode root = new TreeNode(mid.next.val);
        root.right = sortedListToBST(mid.next.next);
        mid.next = null;
        root.left = sortedListToBST(head);
        return root;
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