package leetcode.a024;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for(ListNode p = dummy; p.next != null && p.next.next != null; p = p.next.next) {
            ListNode next1 = p.next;
            ListNode next2 = next1.next;
            ListNode next3 = next2.next;
            p.next = next2;
            next2.next = next1;
            next1.next = next3;
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