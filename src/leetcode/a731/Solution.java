package leetcode.a731;

import java.util.Map;
import java.util.TreeMap;

class MyCalendarTwo {

    static class Segment {
        int end;
        int booked;
        public Segment(int end, int booked) {
            this.end = end;
            this.booked = booked;
        }
    }

    TreeMap<Integer, Segment> calendar = new TreeMap<>();

    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Segment> previous = calendar.lowerEntry(start);
        if(previous != null && previous.getValue().end > start) {
            int key = previous.getKey();
            Segment segment = previous.getValue();
            calendar.remove(key);
            calendar.put(key, new Segment(start, segment.booked));
            calendar.put(start, new Segment(segment.end, segment.booked));
        }
        for (Map.Entry<Integer, Segment> next = calendar.ceilingEntry(start);
                next != null && next.getKey() < end;
                next = calendar.ceilingEntry(next.getValue().end)) {
            if(next.getValue().booked >= 2) {
                return false;
            }
        }
        for (Map.Entry<Integer, Segment> next = calendar.ceilingEntry(start);
                next != null && next.getKey() < end;
                next = calendar.ceilingEntry(start)
                ) {
            if(next.getKey() > start) {
                calendar.put(start, new Segment(next.getKey(), 1));
                start = next.getKey();
            }
            else {
                if (next.getValue().end <= end) {
                    ++ next.getValue().booked;
                    start = next.getValue().end;
                }
                else {
                    Segment segment = next.getValue();
                    calendar.put(start, new Segment(end, 2));
                    calendar.put(end, new Segment(segment.end, 1));
                    start = end;
                }
            }
        }
        if(start != end) {
            calendar.put(start, new Segment(end, 1));
        }
        return true;
    }
}

public class Solution {


    public static void main(String[] args) {
        MyCalendarTwo calendar = new MyCalendarTwo();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(50, 60));
        System.out.println(calendar.book(10, 40));
        System.out.println(calendar.book(5, 15));
        System.out.println(calendar.book(5, 10));
        System.out.println(calendar.book(25, 55));
    }
}