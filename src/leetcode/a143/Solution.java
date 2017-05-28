package leetcode.a143;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        ListNode tail = null;
        ListNode left = null, right = null;
        while (fast.next != null) {
            fast = fast.next;
            if(fast.next == null) {
                tail = slow.next;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        left = head;
        if(tail == null) {
            right = slow.next;
        }
        else {
            right = tail.next;
            tail.next = null;
        }
        slow.next = null;

        while (left.next != null) {
            ListNode tmp = left.next;
            left.next = left.next.next;
            tmp.next = dummy.next;
            dummy.next = tmp;
        }
        left = dummy.next;

        while (left != null) {
            ListNode ln = left.next;
            ListNode rn = right.next;
            left.next = right;
            right.next = tail;
            tail = left;
            left = ln;
            right = rn;
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