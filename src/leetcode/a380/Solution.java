package leetcode.a380;

import java.util.*;

class RandomizedSet {

    List<Integer> nums = new ArrayList<>();
    Map<Integer, Integer> positions = new HashMap<>();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(positions.containsKey(val)) {
            return false;
        }
        positions.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!positions.containsKey(val)) {
            return false;
        }
        int position = positions.get(val);
        positions.remove(val);
        if(position != nums.size() - 1) {
            int end = nums.get(nums.size() - 1);
            nums.set(position, end);
            positions.put(end, position);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(new Random().nextInt(nums.size()));
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