package leetcode.a307;

import java.util.Arrays;
import java.util.List;

class BinaryIndexedTree {
    int[] tree;
    public BinaryIndexedTree(int capacity) {
        tree = new int[capacity + 1];
    }
    public void update(int index, int diff) {
        for(int i = index; i < tree.length; i += (i & (-i))) {
            tree[i] += diff;
        }
    }
    public int query(int index) {
        int sum = 0;
        for(int i = index; i > 0; i -= (i & (-i))) {
            sum += tree[i];
        }
        return sum;
    }
}

class NumArray {
    BinaryIndexedTree bit;
    public NumArray(int[] nums) {
        bit = new BinaryIndexedTree(nums.length);
        for(int i = 0; i < nums.length; ++ i) {
            bit.update(i+1, nums[i]);
        }
    }

    public void update(int i, int val) {
        bit.update(i+1, val - (bit.query(i+1)-bit.query(i)));
    }

    public int sumRange(int i, int j) {
        return bit.query(j+1) - bit.query(i);
    }
}

public class Solution {
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