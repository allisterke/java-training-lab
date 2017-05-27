package leetcode.a138;

import java.util.Arrays;
import java.util.List;

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) {
            return head;
        }
        for(RandomListNode p = head; p != null; p = p.next.next) {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            p.next = node;
        }
        for(RandomListNode p = head; p != null; p = p.next.next) {
            if(p.random != null) {
                p.next.random = p.random.next;
            }
        }
        RandomListNode p = head;
        RandomListNode other = head.next;
        RandomListNode q = other;
        while (true) {
            p.next = q.next;
            if(p.next == null) {
                break;
            }
            p = p.next;
            q.next = p.next;
            q = q.next;
        }
        return other;
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