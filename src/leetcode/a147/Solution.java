package leetcode.a147;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        for(ListNode p = head; p != null; ) {
            ListNode q = dummy;
            for(; q.next != null && q.next.val < p.val; q = q.next)
                ;
            ListNode next = p.next;
            p.next = q.next;
            q.next = p;
            p = next;
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