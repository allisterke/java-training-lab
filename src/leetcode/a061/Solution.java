package leetcode.a061;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;

        int size = 0;
        while (k > 0 && tail.next != null) {
            tail = tail.next;
            -- k;
            ++ size;
        }
        if(tail.next == null) {
            k %= size;
            if(k == 0) {
                return head;
            }

            tail = dummy;
            while (k -- > 0) {
                tail = tail.next;
            }
        }

        head = dummy;
        while (tail.next != null) {
            head = head.next;
            tail = tail.next;
        }
        tail.next = dummy.next;
        dummy.next = head.next;
        head.next = null;

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