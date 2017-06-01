package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ubuntu on 5/24/17.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class ListSortLab {
    public ListNode sort(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode right = sort(mid.next);
        mid.next = null;
        ListNode left = sort(head);
        return merge(left, right);
    }

    ListNode getMid(ListNode head) {
        ListNode fast = head.next, slow = head;
        while (fast != null) {
            fast = fast.next;
            if(fast == null) {
                break;
            }
            else {
                fast = fast.next;
            }
            slow = slow.next;
        }
        return slow;
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
        tail.next = left != null ? left : right;
        return dummy.next;
    }

    public static void main(String[] args) {
        for(int t = 0; t < 10; ++ t) {
            System.out.println(String.format("Case %d:", t));
            Random r = new Random();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < r.nextInt(20) + 5; ++i) {
                nums.add(r.nextInt());
            }
            ListNode head = new ListNode(nums.get(0));
            ListNode p = head;
            for (int i = 1; i < nums.size(); ++i) {
                p.next = new ListNode(nums.get(i));
                p = p.next;
            }

            nums.sort(Integer::compareTo);
            head = new ListSortLab().sort(head);
            p = head;
            for (int i = 0; i < nums.size(); ++i) {
                assert nums.get(i) == p.val;
                System.out.println(String.format("%20d %20d", nums.get(i), p.val));
                p = p.next;
            }
        }
    }
}
