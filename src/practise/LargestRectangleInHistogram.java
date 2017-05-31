package practise;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ubuntu on 5/31/17.
 */
public class LargestRectangleInHistogram {
    static class Pair {
        int height, index;
        public Pair(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
    public int largestRectangleArea(int[] heights) {
        int largest = 0;
        Deque<Pair> leftAscendingStack = new ArrayDeque<>();
        for(int i = 0; i < heights.length; ) {
            leftAscendingStack.push(new Pair(heights[i], i));
            int j = i+1;
            while (j < heights.length && heights[j] >= leftAscendingStack.peek().height) {
                leftAscendingStack.push(new Pair(heights[j], j));
                ++ j;
            }
            int k = j;
            for(; k < heights.length && heights[k] <= heights[k-1]; ++ k)
                ;
            Pair lastLeft = leftAscendingStack.pop();
            Pair lastRight = lastLeft;
            largest = Math.max(largest, lastLeft.height);
            while (!leftAscendingStack.isEmpty() && j < k) {
                if(leftAscendingStack.peek().height > heights[j]) { // select left
                    lastLeft = leftAscendingStack.pop();
                    largest = Math.max(largest, lastLeft.height * (lastRight.index - lastLeft.index + 1));
                }
                else {
                    lastRight = new Pair(heights[j], j);
                    largest = Math.max(largest, lastRight.height * (lastRight.index - lastLeft.index + 1));
                    ++ j;
                }
            }
            if(leftAscendingStack.isEmpty()) { // left is empty iif lastLeft >= all remain right
                for(; j < k; ++ j) {
                    largest = Math.max(largest, heights[j] * (j - lastLeft.index + 1));
                }
                leftAscendingStack.push(new Pair(heights[k-1], lastLeft.index));
            }
            else { // right is empty iif lastRight >= all remain left
                leftAscendingStack.push(k < heights.length ? new Pair(lastRight.height, lastLeft.index) : lastRight);
            }
            i = k; // start again from current valley
        }
        if(!leftAscendingStack.isEmpty()) {
            Pair peek = leftAscendingStack.pop();
            while (!leftAscendingStack.isEmpty()) {
                Pair top = leftAscendingStack.pop();
                largest = Math.max(largest, top.height * (peek.index - top.index + 1));
            }
        }
        return largest;
    }
    public int largestRectangleArea1(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length);
    }
    int largestRectangleArea(int[] heights, int begin, int end) {
        if(begin >= end) {
            return 0;
        }
        int minIndex = begin;
        for(int i = begin; i < end; ++ i) {
            if(heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        return Math.max(
                heights[minIndex] * (end - begin),
                Math.max(
                        largestRectangleArea(heights, begin, minIndex),
                        largestRectangleArea(heights, minIndex+1, end)));
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[] {2,1,5,6,2,3,4,7,2,5,9,5}));
    }
}
