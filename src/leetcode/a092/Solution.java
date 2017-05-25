package leetcode.a092;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p, q;
        int steps = n - m;
        for(p = dummy; steps -- > 0; p = p.next)
            ;
        steps = m - 1;
        for(q = dummy; steps -- > 0; p = p.next, q = q.next)
            ;
        p = p.next;
        head = q.next.next;
        q.next.next = p.next;
        p.next = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = q.next;
            q.next = head;
            head = tmp;
        }
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