package leetcode.a729;

import java.util.Map;
import java.util.TreeMap;

class MyCalendar {
    TreeMap<Integer, Integer> calendar = new TreeMap<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = calendar.lowerEntry(end);
        if(entry == null || entry.getValue() <= start) {
            calendar.put(start, end);
            return true;
        }
        else {
            return false;
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(15, 25));
        System.out.println(calendar.book(20, 30));
    }
}