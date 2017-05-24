package leetcode.a082;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(ListNode p = head; p != null; ) {
            if (p.next == null || p.next.val != p.val) {
                tail.next = p;
                tail = p;
            }
            ListNode q = p.next;
            for (; q != null && q.val == p.val; q = q.next)
                ;
            p = q;
        }
        tail.next = null;
        return dummy.next;
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