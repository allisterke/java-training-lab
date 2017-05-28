package leetcode.a148;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for(int step = 1; ; step <<= 1) {
            ListNode previous = dummy;
            while (previous.next != null) {
                ListNode left = previous.next;
                ListNode mid = previous;
                for (int i = 0; i < step && mid.next != null; ++i) {
                    mid = mid.next;
                }
                ListNode right = mid.next;
                if(right == null) {
                    break;
                }
                ListNode tail = mid;
                for (int i = 0; i < step && tail.next != null; ++i) {
                    tail = tail.next;
                }
                ListNode next = tail.next;
                mid.next = null;
                tail.next = null;
                previous.next = merge(left, right);
                for (; previous.next != null; previous = previous.next)
                    ;
                previous.next = next;
            }
            if(previous == dummy) {
                break;
            }
        }
        return dummy.next;
    }

    ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (left != null && right != null) {
            if(left.val < right.val) {
                tail.next = left;
                left = left.next;
            }
            else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if(left == null) {
            tail.next = right;
        }
        else {
            tail.next = left;
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