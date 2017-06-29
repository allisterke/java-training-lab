package leetcode.a630;

import java.util.*;

public class Solution {
    int[][] courses;
    Map<Integer, Integer> cache = new HashMap<>();

    public int scheduleCourse0(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> Integer.compare(c1[1], c2[1]));
        this.courses = courses;
        cache.clear();
//        return f(0, 0);
        Deque<Integer> td = new ArrayDeque<>();
        Deque<Integer> id = new ArrayDeque<>();
        Deque<Integer> step = new ArrayDeque<>();
        Deque<Integer> r1 = new ArrayDeque<>();
        Deque<Integer> result = new ArrayDeque<>();
        Deque<Integer> kd = new ArrayDeque<>();
        td.push(0);
        id.push(0);
        step.push(0);

        while (!td.isEmpty()) {
            if(id.peek() >= courses.length) {
                td.pop();
                id.pop();
                step.pop();
                result.push(0);
            }
            else {
                switch (step.peek()) {
                    case 0:
                        kd.push((td.peek() << 16) | id.peek());
                        if(!cache.containsKey(kd.peek())) {
                            td.push(td.peek());
                            id.push(id.peek() + 1);
                            step.pop();
                            step.push(1);
                            step.push(0);
                        }
                        else {
                            step.pop();
                            step.push(4);
                        }
                        break;
                    case 1:
                        r1.push(result.pop());
                        if(courses[id.peek()][1] >= td.peek() + courses[id.peek()][0]) {
                            td.push(td.peek() + courses[id.peek()][0]);
                            id.push(id.peek() + 1);
                            step.pop();
                            step.push(2);
                            step.push(0);
                        }
                        else {
                            step.pop();
                            step.push(3);
                        }
                        break;
                    case 2:
                        r1.push(Math.max(result.pop() + 1, r1.pop()));
                        step.pop();
                        step.push(3);
                        break;
                    case 3:
                        cache.put(kd.peek(), r1.pop());
                        step.pop();
                        step.push(4);
                        break;
                    case 4:
                        td.pop();
                        id.pop();
                        step.pop();
                        result.push(cache.get(kd.pop()));
                        break;
                }
            }
        }
        return result.pop();
    }

    int g = 0;
    public int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> Integer.compare(c1[1], c2[1]));
        this.courses = courses;
        this.g = 0;
//        dfs(0, 0, 0);
        Deque<Integer> td = new ArrayDeque<>();
        Deque<Integer> id = new ArrayDeque<>();
        Deque<Integer> cd = new ArrayDeque<>();
        Deque<Integer> sd = new ArrayDeque<>();
        td.push(0);
        id.push(0);
        cd.push(0);
        sd.push(0);
        while (!td.isEmpty()) {
            if(id.peek() >= courses.length) {
                g = Math.max(g, cd.peek());
                td.pop();
                id.pop();
                cd.pop();
                sd.pop();
            }
            else {
                switch (sd.peek()) {
                    case 0:
                        if(courses.length - id.peek() + cd.peek() > g) {
                            if (courses[id.peek()][1] >= td.peek() + courses[id.peek()][0]) {
                                td.push(td.peek() + courses[id.peek()][0]);
                                id.push(id.peek() + 1);
                                cd.push(cd.peek() + 1);
                                sd.pop();
                                sd.push(1);
                                sd.push(0);
                            } else {
                                sd.pop();
                                sd.push(1);
                            }
                        }
                        else {
                            td.pop();
                            id.pop();
                            cd.pop();
                            sd.pop();
                        }
                        break;
                    case 1:
                        td.push(td.peek());
                        id.push(id.peek() + 1);
                        cd.push(cd.peek());
                        sd.pop();
                        sd.push(2);
                        sd.push(0);
                        break;
                    case 2:
                        td.pop();
                        id.pop();
                        cd.pop();
                        sd.pop();
                        break;
                }
            }
        }
        return g;
    }

    void dfs(int t, int i, int cur) {
        if(i >= courses.length) {
            g = Math.max(g, cur);
        }
        else {
            if(courses.length - i + cur <= g) {
                return;
            }
            if(courses[i][1] >= t + courses[i][0]) {
                dfs(t + courses[i][0], i + 1, cur + 1);
            }
            dfs(t, i + 1, cur);
        }
    }

    int f(int t, int i) {
        if(i >= courses.length) {
            return 0;
        }
        int key = (t << 16) | i;
        if(!cache.containsKey(key)) {
            if (courses[i][1] < t + courses[i][0]) {
                cache.put(key, f(t, i + 1));
            } else {
                cache.put(key, Math.max(f(t + courses[i][0], i + 1) + 1, f(t, i + 1)));
            }
        }
        return cache.get(key);
    }

    static class Task {
        int duration, end, start;
        public Task(int duration, int end) {
            this.duration = duration;
            this.end = end;
            this.start = end - duration;
        }
    }

    public int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> Integer.compare(c1[0], c2[0]));
        int count = 0;
        for(int i = 0; i < courses.length; ++ i) {
            if(courses[i][1] >= courses[i][0]) {
                ++ count;
                int end = courses[i][1] - courses[i][0];
                for(int j = i+1; j < courses.length; ++ j) {
                    if(courses[j][1] >= courses[i][1]) {
                        courses[j][1] -= courses[i][0];
                    }
                    else if(courses[j][1] >= end) {
                        courses[j][1] = end;
                    }
                }
            }
        }
        return count;
    }

    public int scheduleCourse3(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> Integer.compare(c1[0], c2[0]));
        int schedule = 0;
        boolean[] used = new boolean[10001];
        for (int[] course : courses) {
            int d = course[0];
            int e = course[1];
            int count = 0;
            for(int i = e; i > 0 && count < d; -- i) {
                if(!used[i]) {
                    ++ count;
                }
            }
            if(count != d) {
                continue;
            }
            count = 0;
            for(int i = e; i > 0 && count < d; -- i) {
                if(!used[i]) {
                    ++ count;
                    used[i] = true;
                }
            }
            ++ schedule;
        }
        return schedule;
    }

    static class SegmentTree {
        class Segment {
            int begin, end;
            int count;
            public Segment(int b, int e, int c) {
                begin = b;
                end = e;
                count = c;
            }
        }
        Segment[] segments;
        public SegmentTree(int capacity) {
            segments = new Segment[capacity << 2];
            init(0, 0, capacity-1);
        }
        private void init(int root, int begin, int end) {
            segments[root] = new Segment(begin, end, 1);
            if(begin < end) {
                int mid = (begin + end) / 2;
                init(root * 2 + 1, begin, mid);
                init(root * 2 + 2, mid+1, end);
                segments[root].count = segments[root * 2 + 1].count + segments[root * 2 + 2].count;
            }
        }
        private int count(int begin, int end) {
            return count(0, begin, end);
        }
        private int count(int root, int begin, int end) {
            if(begin <= segments[root].begin && end >= segments[root].end) {
                return segments[root].count;
            }
            if(begin > segments[root].end || end < segments[root].begin) {
                return 0;
            }
            return count(root * 2 + 1, begin, end) + count(root * 2 + 2, begin, end);
        }
//        public void increment(int index) {
//            increment(0, index);
//        }
//        private void increment(int root, int index) {
//            if(segments[root].begin <= index && segments[root].end > index) {
//                ++ segments[root].count;
//                if(segments[root].begin + 1 != segments[root].end) {
//                    increment(root * 2 + 1, index);
//                    increment(root * 2 + 2, index);
//                }
//            }
//        }
        public void erase(int begin, int end) {
            erase(0, begin-1, end-1);
        }
        private void erase(int root, int begin, int end) {
            if(segments[root].end < begin || segments[root].begin > end) {
                return; // not in range
            }
            if(segments[root].count == 0) {
                return; // no more to erase
            }
            if(segments[root].begin == segments[root].end) {
                segments[root].count = 0;
            }
            else {
                erase(root * 2 + 1, begin, end);
                erase(root * 2 + 2, begin, end);
                segments[root].count = segments[root * 2 + 1].count + segments[root * 2 + 2].count;
            }
        }
        public int findStartPosition(int end, int duration) {
            -- end;
            if(count(0, end) < duration) {
                return 0;
            }
            int left = 0, right = end + 1;
            while (left < right) {
                int mid = (left + right) / 2;
                int empty = count(mid, end);
                if(empty > duration) {
                    left = mid + 1;
                }
                else if(empty < duration) {
                    right = mid;
                }
                else {
                    return mid + 1;
                }
            }
            return 0;
        }
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> Integer.compare(c1[0], c2[0]));
        SegmentTree tree = new SegmentTree(10000);
        int schedule = 0;
        for (int[] course : courses) {
            int d = course[0];
            int e = course[1];
            int start = tree.findStartPosition(e, d);
            if(start <= 0) {
                continue;
            }
            tree.erase(start, e);
            ++ schedule;
        }
        return schedule;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {1,2},
                                new int[] {2,3}
                        },
                        new int[][] {
                                new int[] {100, 200},
                                new int[] {200, 1300},
                                new int[] {1000, 1250},
                                new int[] {2000, 3200}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.scheduleCourse(tests.get(i)));
        }
    }
}