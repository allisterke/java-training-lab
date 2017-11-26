package leetcode.a735;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    static class BidirectionalListNode {
        int val;
        BidirectionalListNode previous, next;
        BidirectionalListNode(int x) { val = x; }
    }

    public int[] asteroidCollision(int[] asteroids) {
        BidirectionalListNode head = new BidirectionalListNode(0);
        BidirectionalListNode tail = head;
        for (int asteroid: asteroids) {
            BidirectionalListNode node = new BidirectionalListNode(asteroid);
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        List<BidirectionalListNode> collisions = new ArrayList<>();
        for (BidirectionalListNode p = head.next; p != null && p.next != null; p = p.next) {
            if(p.val > 0 && p.next.val < 0) {
                collisions.add(p);
            }
        }
        while (!collisions.isEmpty()) {
            List<BidirectionalListNode> next = new ArrayList<>();
            for (BidirectionalListNode node: collisions) {
                if(Math.abs(node.val) == Math.abs(node.next.val)) {
                    if(node.previous != head && node.next.next != null &&
                            node.previous.val > 0 && node.next.next.val < 0) {
                        next.add(node.previous);
                    }
                    node.previous.next = node.next.next;
                    if(node.next.next != null) {
                        node.next.next.previous = node.previous;
                    }
                }
                else {
                    if(Math.abs(node.val) < Math.abs(node.next.val)) {
                        node.val = node.next.val;
                    }
                    node.next = node.next.next;
                    if(node.next != null) {
                        node.next.previous = node;
                    }
                    if(node.previous != head && node.previous.val > 0 && node.val < 0) {
                        next.add(node.previous);
                    }
                    if(node.next != null && node.val > 0 && node.next.val < 0) {
                        next.add(node);
                    }
                }
            }
            collisions = next;
        }
        List<Integer> result = new ArrayList<>();
        for (BidirectionalListNode p = head.next; p != null; p = p.next) {
            result.add(p.val);
        }
        int[] a = new int[result.size()];
        for(int i = 0; i < result.size(); ++ i) {
            a[i] = result.get(i);
        }
        return a;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {5, 10, -5},
                        {8, -8},
                        {10, 2, -5}
                }
        );
        List<int[]> results = Arrays.asList(
                new int[][] {
                        {5, 10},
                        {},
                        {10}
                }
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.asteroidCollision(tests.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}