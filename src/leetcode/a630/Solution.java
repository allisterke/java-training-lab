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
    public int scheduleCourse(int[][] courses) {
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

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
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