package leetcode.a086;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode tail1 = dummy1, tail2 = dummy2;
        for(; head != null; head = head.next) {
            if(head.val < x) {
                tail1.next = head;
                tail1 = tail1.next;
            }
            else {
                tail2.next = head;
                tail2 = tail2.next;
            }
        }
        tail1.next = dummy2.next;
        tail2.next = null;
        return dummy1.next;
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