package leetcode.a682;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(String op : ops) {
            if(op.equals("+")) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 1) - list.get(list.size() - 3));
            }
            else if(op.equals("D")) {
                list.add(list.get(list.size() - 1) + (list.get(list.size() - 1) - list.get(list.size() - 2))* 2);
            }
            else if(op.equals("C")) {
                list.remove(list.size() - 1);
            }
            else {
                list.add(list.get(list.size() - 1) + Integer.valueOf(op));
            }
        }
        return list.get(list.size() - 1);
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