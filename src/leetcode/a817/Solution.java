package leetcode.a817;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int g : G) {
            set.add(g);
        }
        int nc = 0;
        boolean linked = false;
        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p.val)) {
                if (!linked) {
                    ++ nc;
                    linked = true;
                }
            }
            else {
                linked = false;
            }
        }
        return nc;
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