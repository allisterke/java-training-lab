package leetcode.a628;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int maximumProduct(int[] nums) {
        PriorityQueue<Integer> positive = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        int zeros = 0;
        for(int n : nums) {
            if(n > 0) {
                positive.add(n);
            }
            else if(n < 0) {
                negative.add(n);
            }
            else {
                ++ zeros;
            }
        }
        if(positive.isEmpty()) { // no positive
            if(zeros > 0) {
                return 0;
            }
            else {
                while (negative.size() > 3) {
                    negative.poll();
                }
                return negative.poll() * negative.poll() * negative.poll();
            }
        }
        else if(positive.size() == 1) { // only one positive
            return negative.size() >= 2 ? negative.poll() * negative.poll() * positive.poll() : 0;
        }
        else if(positive.size() == 2) { // only two positive
            if(negative.isEmpty()) {
                return 0;
            }
            if(negative.size() == 1) {
                return zeros > 0 ? 0 : positive.poll() * positive.poll() * negative.poll();
            }
            else {
                return negative.poll() * negative.poll() * positive.poll();
            }
        }
        else {
            if(negative.size() <= 1) {
                return positive.poll() * positive.poll() * positive.poll();
            }
            else {
                int a = positive.poll();
                int b = positive.poll();
                int c = positive.poll();
                return Math.max(a * b * c,
                        a * negative.poll() * negative.poll()
                    );
            }
        }
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1,2,-3},
                new int[] {1,-2,-3,4},
                new int[] {-1,-2,1,2,3}
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maximumProduct(tests.get(i)));
        }
    }
}